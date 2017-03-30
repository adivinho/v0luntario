package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.AddUserRequest;
import v0luntario.api.GenericReply;
import v0luntario.api.UserListReply;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.UserMapper;
import v0luntario.services.UserService;

import java.io.EOFException;

/**
 * Created by silvo on 3/24/17.
 */
@RestController
@EnableAutoConfiguration
public class UsersController {
    private static final Logger logger =  LoggerFactory.getLogger(UsersController.class);
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(path="/users/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllUsers(){
        logger.info("=> /users/all request has come ...");
        UserListReply reply = new UserListReply();
        for(UsersEntity au: userService.getAllUsers()){
            reply.users.add(userMapper.fromInternal(au));
        }
        return reply;
    }

    @RequestMapping(path="/users/byid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getUserById(@PathVariable String userid ){
        logger.info("=> /users/byid request has come for id: "+userid);
        UserListReply reply = new UserListReply();
        reply.users.add(userMapper.fromInternal(userService.getUserById(userid)));
        return reply;
    }

    @RequestMapping(path="/users/del/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUser(@PathVariable String userid ){
        GenericReply rep = new GenericReply();
        try{
            userService.delUser(userid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/users/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply addUser(@RequestBody AddUserRequest req) throws EOFException {
            logger.info("=> /users/add request has come");
            UserListReply rep = new UserListReply();
            try{
                UsersEntity ue = userService.addUser(userMapper.toInternal(req.user));
                rep.users.add(userMapper.fromInternal(ue));
            }
            catch(Exception e){
                rep.retcode = -1;
                rep.error_message = e.getMessage();
                logger.error("=> Error adding an user. Exception: "+e.getMessage());
            }
            return rep;
    }

    @RequestMapping("/hello")
    String home() {
        logger.info("=> /hello request has come");
        return "Hello User!\n";
    }
}