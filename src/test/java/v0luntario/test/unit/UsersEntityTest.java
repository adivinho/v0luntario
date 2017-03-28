package v0luntario.test.unit;

import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.UserService;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by silvo on 3/25/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersEntityTest {
    @Autowired
    private UserService us;

    @Test
    public void findUsersTest() throws Exception {
        int count = us.getAllUsers().size();
        assert(count>=6);
    }

    @Test
    public void addUserTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Long user_id = EntityIdGenerator.random();
        UsersEntity ue = new UsersEntity(String.valueOf(user_id));
        UserdetailsEntity ud = new UserdetailsEntity(String.valueOf(user_id));
        GroupsEntity ge = new GroupsEntity();

        ue.setCreatedBy("2000");
        ue.setUsername("username"+EntityIdGenerator.randomShort());
        ue.setEmail("ttt"+EntityIdGenerator.randomShort()+"@test.com");
        ue.setRole(UsersEntity.Roles.User);
        ue.setPasswordHash("SuperStrongPassword" + user_id);

        ud.setFirstName("UserFirstName");
        ud.setLastName("UserLastName");
        ud.setMidInit("UserMidInit");
        ud.setPhone("380993211212");
        ud.setCountry("Ukraine");
        ud.setCity("Kiev");
        ud.setSex("male");
        ud.setNotes(ue.getEmail()+" "+ue.getUsername());
        Calendar calendar = Calendar.getInstance();
        Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
        ud.setActivationDate(dateNow);

        ge.setGroupId("1001");
        List<GroupsEntity> group = new ArrayList<>();
        group.add(ge);
        ue.setGroupsList(group);
        ue.setUserdetails(ud);
        us.addUser(ue);

        UsersEntity u = us.getUserById(String.valueOf(user_id));
        System.out.println("====> Passed "+us.getClass());
        assertNotNull("======> New user not found!", u);

        // ------------  Perform Update  ---------
        ue.setUsername(EntityIdGenerator.randomShort() + "TestEditedLogin");
        ue.setRole(UsersEntity.Roles.Admin);
        ud.setFirstName("TestedUpdatedFirstName");
        ud.setLastName("TestedUpdatedLastName");
        ue.setUserdetails(ud);
        us.updateUser(ue);

        // ------------  Perform Select ---------
        List<UsersEntity> user2 = us.getUserByName(ud.getFirstName(), ud.getLastName());
        int count = user2.size();
        assert(count>=1);
//        Assert.assertSame(ue, user2);

        // ------------ Perform customer dropping ---------
        us.delUser(ue.getUserId());
        u = us.getUserById(String.valueOf(user_id));
        assertNull("=> Can not delete user!", u);
    }
}
