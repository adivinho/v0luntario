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


    private UsersEntity newUser(UserReply ur) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UsersEntity au = new UsersEntity();
        UserdetailsEntity ud = new UserdetailsEntity();
        String createdBy = "2000"; //TODO set the correct value after authorization
        if (ur.user_id != null) au.setUserId(ur.user_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !userRepository.exists(String.valueOf(id));
            }
            au.setUserId(String.valueOf(id));
        }
        au.setCreatedBy(createdBy);
        au.setUsername(ur.login != null?ur.login:"login"+EntityIdGenerator.randomShort());
        au.setEmail(ur.email != null?ur.email:"fakeemail"+EntityIdGenerator.randomShort()+"@fake.com");
        au.setRole(ur.role != null?Enum.valueOf(UsersEntity.Roles.class, ur.role):UsersEntity.Roles.User);
        au.setPasswordHash(ur.password != null?EntityIdGenerator.makeSHA1Hash(ur.password):EntityIdGenerator.makeSHA1Hash("SuperStrongPassword"));

        ud.setUserId(ur.user_id);
        ud.setFirstName(ur.firstName != null?ur.firstName:"Silvio");
        ud.setLastName(ur.lastName != null?ur.lastName:"Rodrigues");
        ud.setMidInit(ur.midInit);
        ud.setSex(ur.sex);
        ud.setCountry(ur.country);
        ud.setCity(ur.city);
        ud.setAddress(ur.address);
        ud.setMobile(ur.mobile);
        ud.setMobile2(ur.mobile2);
        ud.setNotes(ur.notes);
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
            lu.createdBy = u.getCreatedBy();
            if (ude != null) {
                lu.firstName = u.getUserdetails().getFirstName();
                lu.lastName = u.getUserdetails().getLastName();
                lu.country = u.getUserdetails().getCountry();
                lu.city = u.getUserdetails().getCity();
                lu.address = u.getUserdetails().getAddress();
                lu.phone = u.getUserdetails().getPhone();
                lu.midInit = u.getUserdetails().getMidInit();
                lu.sex = u.getUserdetails().getSex();
                lu.mobile = u.getUserdetails().getMobile();
                lu.notes = u.getUserdetails().getNotes();
                lu.activationDate = u.getUserdetails().getActivationDate().toString();
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
        //check if it exists
        if (lu.user_id != null) {
            logger.info("=> Provided %s user_id ",lu.user_id);
            au = userRepository.findOne(lu.user_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating new user ...");
            au = newUser(lu);
        }
        else {
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
        }
        return au;
    }
}
