package test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.GroupsService;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.assertNull;

/**
 * Created by silvo on 3/21/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
public class GroupsEntityTest {
    private GroupsService gs = new GroupsService();

    @Test
    public void getAllGroupsTest(){
        List<GroupsEntity> lg = gs.getAllGroups();
        assert(lg.size()>1);
    }

    @Test
    public void addGroupTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Long group_id = EntityIdGenerator.random();
        GroupsEntity ge = new GroupsEntity(String.valueOf(group_id));
        ge.setGroupName("New Test Group");
        ge.setDescription("Made for testing purpose");

        gs.add_group(ge);

        GroupsEntity group = gs.getGroupById(ge.getGroupId());
        Assert.assertSame(ge, group);

        ge.setGroupName(EntityIdGenerator.randomShort()+"TestEditedGroup");
        ge.setDescription("New description ...");
        gs.edit_group(ge);

        gs.del_group(ge);

        GroupsEntity group2 = gs.getGroupById(ge.getGroupId());
        assertNull("Can't find dropped user", group2);
    }
}
