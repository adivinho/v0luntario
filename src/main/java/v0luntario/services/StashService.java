package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.MovementsEntity;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.StashRepository;


import java.util.List;

/**
 * Created by silvo on 3/26/17.
 */
@Service
public class StashService {
    private static final Logger logger =  LoggerFactory.getLogger(StashService.class);
    @Autowired
    StashRepository stashRepository;

    public List<StashEntity> getAllStash(){
        return  stashRepository.findAll();
    }

    public StashEntity getStashByUserId(String id) {
        StashEntity s = stashRepository.findOne(id);
        return s;
    }

    public StashEntity addStash(StashEntity ce) {
        logger.info("=> Adding stash with id %s", ce.getStashId());
        ce = stashRepository.save(ce);
        return ce;
    }

    public void delStash(String id) {
        StashEntity u = stashRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting stash with id %s", u.getStashId());
            stashRepository.delete(id);
        }
    }
}
