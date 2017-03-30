package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.GroupReply;
import v0luntario.api.UserReply;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.GroupRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by silvo on 3/29/17.
 */

@Component
public class GroupMapper {
    private static final Logger logger = LoggerFactory.getLogger(GroupMapper.class);
    @Autowired
    GroupRepository groupRepository;

    public GroupReply fromInternal(GroupsEntity g) {
        GroupReply lg = null;
        logger.info("=> fromInternal procedure executed ...");
        if (g != null) {
            lg = new GroupReply();

            int i = g.getUsersList().size();

            lg.group_id = g.getGroupId();
            lg.group_name = g.getGroupName();
            lg.description = g.getDescription();

            if (i > 0){
                for (UsersEntity ue : g.getUsersList()){
                    lg.users.add(ue.getUserId());
                }
            }
        }
        return lg;
    }

    public GroupsEntity toInternal(GroupReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        GroupsEntity au = null;
        if (lu != null) {
            logger.info("=> Provided %s group_id ",lu.group_id);
            au = groupRepository.findOne(lu.group_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating new group ...");
            au = newGroup(lu);
        }
        logger.debug("=> Updating existing group ...");
//        au.setGroupName(lu.group_name);
//        au.setDescription(lu.description);
        logger.debug("=> Group: " +au);
        return au;
    }

    private GroupsEntity newGroup(GroupReply gr) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        GroupsEntity au = new GroupsEntity();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            logger.debug("=> Generated new ID:"+id);
            idOK = !groupRepository.exists(String.valueOf(id));
        }
        au.setGroupId(String.valueOf(id));
        if (gr.group_name != null) au.setGroupName(gr.group_name);
            else au.setGroupName("NewGroup"+EntityIdGenerator.randomShort());
        if (gr.description != null) au.setDescription(gr.description);
            else {
            Calendar calendar = Calendar.getInstance();
            Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
            au.setDescription("It's the newest group at " + dateNow);
            }
        return au;
    }
}