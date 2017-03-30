package v0luntario.services;

import org.hibernate.annotations.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.UserReply;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.UserRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by silvo on 3/24/17.
 */
@Component
public class UserMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UserMapper.class);
    @Autowired
    UserRepository userRepository;


    private UsersEntity newUser() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String createdBy = "2000";
        UsersEntity au = new UsersEntity();
        UserdetailsEntity ud = new UserdetailsEntity();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            logger.debug("Generated new ID:"+id);
            idOK = !userRepository.exists(String.valueOf(id));
        }
        au.setUserId(String.valueOf(id));
        au.setPasswordHash("*");
        au.setCreatedBy(createdBy);
        au.setEmail("ttt"+EntityIdGenerator.randomShort()+"@test.com");
        au.setRole(UsersEntity.Roles.User);
        au.setPasswordHash(EntityIdGenerator.makeSHA1Hash("SuperStrongPassword"));

        ud.setUserId(String.valueOf(id));
        ud.setFirstName("Silvio");
        ud.setLastName("Rodrigues");
        ud.setNotes("none");
        Calendar calendar = Calendar.getInstance();
        Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
        ud.setActivationDate(dateNow);
        au.setUserdetails(ud);
        return au;
    }

    public UserReply fromInternal(UsersEntity u) {
        UserReply lu = null;
        logger.info("=> UserMapper.java: fromInternal procedure executed ...");
        if (u != null) {
            lu = new UserReply();
            UserdetailsEntity ude = u.getUserdetails();
            int i = u.getGroupsList().size();

            lu.login = u.getUsername();
            lu.user_id = u.getUserId();
            lu.email = u.getEmail();
            lu.role = u.getRole().toString();
            if (ude != null) {
                lu.firstName = u.getUserdetails().getFirstName();
                lu.lastName = u.getUserdetails().getLastName();
            }
            if (i > 0){
                for (GroupsEntity ge : u.getGroupsList()){
                    lu.groups.add(ge.getGroupId());
                }
            }
        }
        return lu;
    }

    public UsersEntity toInternal(UserReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UsersEntity au = null;
        //first, check if it exists
        if (lu != null) {
            logger.info("=> Provided %s user_id ",lu.user_id);
            au = userRepository.findOne(lu.user_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating new user ...");
            au = newUser();
        }
        logger.debug("=> Updating existing user ...");
        au.setUsername(lu.login);
        au.getUserdetails().setFirstName(lu.firstName);
        au.getUserdetails().setLastName(lu.lastName);
        au.setEmail(lu.email);
//        if (lu.isLibrarian) {
//            Ugroup g = groupRepository.findOne(LIBRARIANS_GROUP_ID);
//            g.getAppuserList().add(au);
//            au.getUgroupList().add(g);
//        }
        return au;
    }
}
