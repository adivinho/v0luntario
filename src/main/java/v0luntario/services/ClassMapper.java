package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.ClassReply;
import v0luntario.jpa.ClassesEntity;
import v0luntario.repository.ClassRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silvo on 4/2/17.
 */
@Component
public class ClassMapper {
    private static final Logger logger =  LoggerFactory.getLogger(ClassMapper.class);
    @Autowired
    ClassRepository classRepository;

    public ClassReply fromInternal(ClassesEntity ce) {
        ClassReply cr = null;
        if (ce != null) {
            cr = new ClassReply();
            cr.class_id  = ce.getClassId();
            cr.name = ce.getName();
            cr.description = ce.getDescription();
        }
        return cr;
    }

    public ClassesEntity toInternal(ClassReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ClassesEntity au = null;
        if (lu.class_id != null) {
            logger.info("=> Provided %s class_id ",lu.class_id);
            au = classRepository.findOne(lu.class_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new class ...");
            au = newClass(lu);
        }
        else {
            logger.debug("=> Updating existing class ...");
            au.setName(lu.name);
            au.setDescription(lu.description);
        }
        return au;
    }

    private ClassesEntity newClass(ClassReply cr) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ClassesEntity ce = new ClassesEntity();
        if (cr.class_id != null) ce.setClassId(cr.class_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !classRepository.exists(String.valueOf(id));
            }
            ce.setClassId(String.valueOf(id));
        }

        ce.setName(cr.name);
        ce.setDescription(cr.description);
        return ce;
    }
}
