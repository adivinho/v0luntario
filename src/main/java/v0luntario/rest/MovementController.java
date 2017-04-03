package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.AddMovementRequest;
import v0luntario.api.GenericReply;
import v0luntario.api.MovementListReply;
import v0luntario.jpa.MovementsEntity;
import v0luntario.services.MovementMapper;
import v0luntario.services.MovementService;

import java.io.EOFException;

/**
 * Created by silvo on 4/3/17.
 */
@RestController
@EnableAutoConfiguration
public class MovementController {
    private static final Logger logger =  LoggerFactory.getLogger(MovementController.class);

    @Autowired
    MovementService movementService;
    @Autowired
    MovementMapper movementMapper;

    @RequestMapping(path="/movement/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovementListReply getAllMovements(){
        MovementListReply reply = new MovementListReply();
        for(MovementsEntity ue: movementService.getAllMovements()){
            reply.movements.add(movementMapper.fromInternal(ue));
        }
        return reply;
    }

    @RequestMapping(path="/movement/byid/{movementid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovementListReply getUnitById(@PathVariable String movementid ){
        logger.info("=> /movement/byid request has come for id: "+movementid);
        MovementListReply reply = new MovementListReply();
        reply.movements.add(movementMapper.fromInternal(movementService.getMovementById(movementid)));
        return reply;
    }

    @RequestMapping(path="/movement/del/{movementid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delMovement(@PathVariable String movementid ){
        GenericReply rep = new GenericReply();
        try{
            movementService.delMovement(movementid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error dropping a movement. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }


    @RequestMapping(path="/movement/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovementListReply addClass(@RequestBody AddMovementRequest req) throws EOFException {
        logger.info("=> /movement/add request has come");
        MovementListReply rep = new MovementListReply();
        try{
            MovementsEntity ue = movementService.addMovement(movementMapper.toInternal(req.movement));
            rep.movements.add(movementMapper.fromInternal(ue));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a movement. Exception: "+e.getMessage());
        }
        return rep;
    }
}
