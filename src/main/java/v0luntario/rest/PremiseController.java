package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.AddPremiseRequest;
import v0luntario.api.GenericReply;
import v0luntario.api.PremiseListReply;
import v0luntario.jpa.PremisesEntity;
import v0luntario.services.PremiseMapper;
import v0luntario.services.PremiseService;

import java.io.EOFException;

/**
 * Created by silvo on 4/3/17.
 */
@RestController
@EnableAutoConfiguration
public class PremiseController {
    private static final Logger logger =  LoggerFactory.getLogger(PremiseController.class);

    @Autowired
    PremiseService premiseService;
    @Autowired
    PremiseMapper premiseMapper;

    @RequestMapping(path="/premise/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PremiseListReply getAllPremises(){
        PremiseListReply reply = new PremiseListReply();
        for(PremisesEntity se: premiseService.getAllPremises()){
            reply.premises.add(premiseMapper.fromInternal(se));
        }
        return reply;
    }

    @RequestMapping(path="/premise/byid/{premiseid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PremiseListReply getPremiseById(@PathVariable String premiseid ){
        logger.info("=> /premise/byid request has come for id: "+premiseid);
        PremiseListReply reply = new PremiseListReply();
        reply.premises.add(premiseMapper.fromInternal(premiseService.getPremiseById(premiseid)));
        return reply;
    }

    @RequestMapping(path="/premise/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PremiseListReply addClass(@RequestBody AddPremiseRequest req) throws EOFException {
        logger.info("=> /premise/add request has come");
        PremiseListReply rep = new PremiseListReply();
        try{
            PremisesEntity ue = premiseService.addPremise(premiseMapper.toInternal(req.premise));
            rep.premises.add(premiseMapper.fromInternal(ue));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a premise. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/premise/del/{premiseid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUnit(@PathVariable String premiseid ){
        GenericReply rep = new GenericReply();
        try{
            premiseService.delPremise(premiseid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error dropping a premise. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }
}
