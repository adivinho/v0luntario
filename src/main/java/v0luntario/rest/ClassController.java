package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.AddClassRequest;
import v0luntario.api.ClassListReply;
import v0luntario.api.GenericReply;
import v0luntario.jpa.ClassesEntity;
import v0luntario.services.ClassMapper;
import v0luntario.services.ClassService;

import java.io.EOFException;

/**
 * Created by silvo on 4/2/17.
 */
@RestController
@EnableAutoConfiguration
public class ClassController {
    private static final Logger logger =  LoggerFactory.getLogger(ClassController.class);

    @Autowired
    ClassService classService;
    @Autowired
    ClassMapper classMapper;

    @RequestMapping(path="/class/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClassListReply getAllClasses(){
        ClassListReply reply = new ClassListReply();
        for(ClassesEntity se: classService.getAllClasses()){
            reply.classes.add(classMapper.fromInternal(se));
        }
        return reply;
    }

    @RequestMapping(path="/class/byid/{classid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClassListReply getClassById(@PathVariable String classid ){
        logger.info("=> /class/byid request has come for id: "+classid);
        ClassListReply reply = new ClassListReply();
        reply.classes.add(classMapper.fromInternal(classService.getClassById(classid)));
        return reply;
    }

    @RequestMapping(path="/class/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClassListReply addClass(@RequestBody AddClassRequest req) throws EOFException {
        logger.info("=> /class/add request has come");
        ClassListReply rep = new ClassListReply();
        try{
            ClassesEntity ue = classService.addClass(classMapper.toInternal(req.classname));
            rep.classes.add(classMapper.fromInternal(ue));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a class. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/class/del/{classid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delClass(@PathVariable String classid ){
        GenericReply rep = new GenericReply();
        try{
            classService.delClass(classid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error dropping a class. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping("/class")
    String home() {
        return "Hello from Class!\n";
    }
}
