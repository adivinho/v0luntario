package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import v0luntario.api.StashListReply;
import v0luntario.api.UserListReply;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.services.StashMapper;
import v0luntario.services.StashService;
import v0luntario.services.UserMapper;
import v0luntario.services.UserService;

/**
 * Created by silvo on 3/26/17.
 */
@RestController
@EnableAutoConfiguration
public class StashController {
    private static final Logger logger =  LoggerFactory.getLogger(StashController.class);

    @Autowired
    StashService stashService;

    @Autowired
    StashMapper stashMapper;

    @RequestMapping(path="/stash/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StashListReply getAllStashes(){
        StashListReply reply = new StashListReply();
        for(StashEntity se: stashService.getAllStash()){
            reply.stash.add(stashMapper.fromInternal(se));
        }
        return reply;
    }

    @RequestMapping(path="/stash/byuserid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StashListReply getStashById(@PathVariable String userid ){
        logger.info("=> /stash/byuserid request has come for id: "+userid);
        StashListReply reply = new StashListReply();
        reply.stash.add(stashMapper.fromInternal(stashService.getStashByUserId(userid)));
        return reply;
    }

    @RequestMapping("/stash")
    String home() {
        return "Hello from Stash!\n";
    }
}
