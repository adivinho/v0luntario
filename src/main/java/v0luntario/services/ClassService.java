package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.ClassesEntity;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.ClassRepository;
import v0luntario.repository.StashRepository;

import java.util.List;

/**
 * Created by silvo on 4/2/17.
 */
@Service
public class ClassService {
    private static final Logger logger =  LoggerFactory.getLogger(ClassService.class);
    @Autowired
    ClassRepository classRepository;

    public List<ClassesEntity> getAllClasses(){
        return  classRepository.findAll();
    }

    public ClassesEntity getClassById(String id) {
        ClassesEntity c = classRepository.findOne(id);
        return c;
    }

    public ClassesEntity addClass(ClassesEntity ce) {
        logger.info("=> Adding class %s with id %s", ce.getName(), ce.getClassId());
        ce = classRepository.save(ce);
        return ce;
    }

    public void delClass(String id) {
        ClassesEntity u = classRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting class %s with id %s", u.getName(), u.getClassId());
            classRepository.delete(id);
        }
    }
}
