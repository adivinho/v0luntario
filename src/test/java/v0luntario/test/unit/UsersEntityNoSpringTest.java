package v0luntario.test.unit;

import org.eclipse.persistence.exceptions.PersistenceUnitLoadingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.UserServiceNoSpring;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.assertNull;

/**
 * Created by silvo on 3/26/17.
 */

public class UsersEntityNoSpringTest {
        private UserServiceNoSpring us = new UserServiceNoSpring();

        @Test
        public void addUserTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
            Long user_id = EntityIdGenerator.random();
            UsersEntity ue = new UsersEntity(String.valueOf(user_id));
            ue.setCreatedBy("2000");
            ue.setUsername("username" + EntityIdGenerator.randomShort());
            ue.setEmail("ttt" + EntityIdGenerator.randomShort() + "@test.com");
            ue.setPasswordHash("SuperStrongPassword" + user_id);
            ue.setRole(UsersEntity.Roles.User);
            ue.setPasswordHash(EntityIdGenerator.makeSHA1Hash("SuperStrongPassword"));

            us.add_user(ue);

            UserdetailsEntity ud = new UserdetailsEntity(ue.getUserId());
            ud.setFirstName("UserFirstName");
            ud.setLastName("UserLastName");
            ud.setMidInit("UserMidInit");
            ud.setPhone("380993211212");
            ud.setNotes(ue.getEmail());
            ud.setCountry("Ukraine");
            ud.setCity("Kiev");
            ud.setSex("male");

            us.add_userdetails(ud);

            // ------------  Perform Select ---------
            UsersEntity user = us.getUserById(ue.getUserId());
            Assert.assertSame(ue, user);

            // ------------  Perform Update  ---------
            ue.setUsername(EntityIdGenerator.randomShort() + "TestEditedLogin");
            ue.setRole(UsersEntity.Roles.Admin);
            us.edit_user(ue, ud);

            // ------------  Perform Select ---------
            UsersEntity user2 = us.getUserByUsername(ue.getUsername());
            Assert.assertSame(ue, user2);

            // ------------  Remove Entry  ---------
            us.del_user(ue, ud);

            // ------------  Perform Select ---------

            UsersEntity user3 = us.getUserById(ue.getUserId());
            assertNull("Can't find dropped user", user3);
        }



    @Test
        public void getAllUsersTest() throws PersistenceUnitLoadingException {
            List<UsersEntity> lu = us.getAllUsers();
            assert (lu.size() > 6);
        }
    }


