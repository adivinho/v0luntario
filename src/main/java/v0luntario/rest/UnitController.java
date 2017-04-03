package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import v0luntario.api.AddUnitRequest;
import v0luntario.api.UnitListReply;
import v0luntario.api.GenericReply;
import v0luntario.api.UnitReply;
import v0luntario.jpa.UnitsEntity;
import v0luntario.services.UnitMapper;
import v0luntario.services.UnitService;
import v0luntario.utils.EntityIdGenerator;

import java.io.EOFException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silvo on 4/3/17.
 */
@RestController
@EnableAutoConfiguration
public class UnitController {
    private static final Logger logger =  LoggerFactory.getLogger(UnitController.class);

    @Autowired
    UnitService unitService;
    @Autowired
    UnitMapper unitMapper;

    @RequestMapping(path="/unit/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UnitListReply getAllUnits(){
        UnitListReply reply = new UnitListReply();
        for(UnitsEntity ue: unitService.getAllUnits()){
            reply.units.add(unitMapper.fromInternal(ue));
        }
        return reply;
    }

    @RequestMapping(path="/unit/byid/{unitid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UnitListReply getUnitById(@PathVariable String unitid ){
        logger.info("=> /unit/byid request has come for id: "+unitid);
        UnitListReply reply = new UnitListReply();
        reply.units.add(unitMapper.fromInternal(unitService.getUnitById(unitid)));
        return reply;
    }

    @RequestMapping(path="/unit/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UnitListReply addClass(@RequestBody AddUnitRequest req) throws EOFException {
        logger.info("=> /class/add request has come");
        UnitListReply rep = new UnitListReply();
        try{
            UnitsEntity ue = unitService.addUnit(unitMapper.toInternal(req.unit));
            rep.units.add(unitMapper.fromInternal(ue));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a unit. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/unit/del/{unitid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUnit(@PathVariable String unitid ){
        GenericReply rep = new GenericReply();
        try{
            unitService.delUnit(unitid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error dropping a unit. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }
}
