package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.GroupRepository;
import v0luntario.repository.UserRepository;
import v0luntario.repository.UserdetailsRepository;

import java.util.List;

/**
 * Created by silvo on 3/29/17.
 */

@Service
public class GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    GroupRepository groupRepository;

    public List<GroupsEntity> getAllGroups(){
        return groupRepository.findAll();
    }

    public GroupsEntity getGroupById(String id) {
        GroupsEntity g = groupRepository.findOne(id);
        return g;
    }

    public void delGroup(String id) {
        GroupsEntity g = groupRepository.findOne(id);
        if (g != null) {
            logger.debug("=> Deleting users %s with id %s", g.getGroupName(), g.getGroupId());
            groupRepository.delete(id);
        }
    }

    public GroupsEntity addGroup(GroupsEntity ge) {
        logger.info("=> Adding a group "+ge.getGroupName()+" with id "+ge.getGroupId());
        ge = groupRepository.save(ge);
        return ge;
    }
}