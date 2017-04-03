package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.UserRepository;
import v0luntario.repository.UserdetailsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/24/17.
 */
@Service
public class UserService {
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserdetailsRepository detailsRepository;

    public List<UsersEntity> getAllUsers(){
        return  userRepository.findAll();
    }

    public UsersEntity getUserById(String id) {
        UsersEntity u = userRepository.findOne(id);
        return u;
    }

    public List<UsersEntity> getUserByName(String firstName, String lastName) {
        List<UserdetailsEntity> udl = detailsRepository.findByFirstNameAndLastName(firstName, lastName);
        List<UsersEntity> res = new ArrayList<>();
        udl.forEach((ud) -> {
            res.add(userRepository.findOne(ud.getUserId()));
        });
        return res;
    }

    public UsersEntity addUser(UsersEntity ue) {
        logger.info("=> Adding user %s with id %s", ue.getUsername(), ue.getUserId());
        ue = userRepository.save(ue);
        return ue;
    }

    public UsersEntity updateUser(UsersEntity ue) {
        logger.info("=> Updating user %s with id %s", ue.getUsername(), ue.getUserId());
        ue = userRepository.save(ue);
        return ue;
    }

    public void delUser(String id) {
        UsersEntity u = userRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting users %s with id %s", u.getUsername(), u.getUserId());
    //   => Records in user_group table are dropped by Cascade <=
    //        List<GroupsEntity> gl = u.getGroupsList();
    //        gl.clear();
            detailsRepository.delete(id);
            userRepository.delete(id);
        }
    }

}
