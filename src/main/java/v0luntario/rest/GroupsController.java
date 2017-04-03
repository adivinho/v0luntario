package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.*;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.GroupMapper;
import v0luntario.services.GroupService;

import java.io.EOFException;

/**
 * Created by silvo on 3/29/17.
 */
@RestController
@EnableAutoConfiguration
public class GroupsController {
    private static final Logger logger =  LoggerFactory.getLogger(UsersController.class);
    @Autowired
    GroupService groupService;

    @Autowired
    GroupMapper groupMapper;

    @RequestMapping(path="/groups/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GroupListReply getAllGroups(){
        logger.info("=> /groups/all request has come ...");
        GroupListReply reply = new GroupListReply();
        for(GroupsEntity ge: groupService.getAllGroups()){
            reply.groups.add(groupMapper.fromInternal(ge));
        }
        return reply;
    }

    @RequestMapping(path="/groups/byid/{groupid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GroupListReply getGroupById(@PathVariable String groupid ){
        logger.info("=> /groups/byid request has come for id: "+groupid);
        GroupListReply reply = new GroupListReply();
        reply.groups.add(groupMapper.fromInternal(groupService.getGroupById(groupid)));
        return reply;
    }

    @RequestMapping(path="/groups/del/{groupid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delGroup(@PathVariable String groupid ){
        GenericReply rep = new GenericReply();
        try{
            groupService.delGroup(groupid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/groups/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GroupListReply addGroup(@RequestBody AddGroupRequest req) throws Exception {
        logger.info("=> /groups/add request has come "+req);
        GroupListReply rep = new GroupListReply();
        try{
            GroupsEntity ge = groupService.addGroup(groupMapper.toInternal(req.group));
            rep.groups.add(groupMapper.fromInternal(ge));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a group. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/groups/edit",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GroupListReply editGroup(@RequestBody AddGroupRequest req) throws Exception {
        logger.info("=> /groups/edit request has come "+req);
        GroupListReply rep = new GroupListReply();
        try{
            GroupsEntity ge = groupService.updateGroup(groupMapper.toInternal(req.group));
            rep.groups.add(groupMapper.fromInternal(ge));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error editing the group. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/ghello", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String home() {
        logger.info("=> /hello request has come");
        return "Hello Group!\n";
    }
}
