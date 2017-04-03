package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.PremisesEntity;
import v0luntario.repository.PremiseRepository;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
@Service
public class PremiseService {
    private static final Logger logger =  LoggerFactory.getLogger(PremiseService.class);
    @Autowired
    PremiseRepository premiseRepository;

    public List<PremisesEntity> getAllPremises(){
        return  premiseRepository.findAll();
    }

    public PremisesEntity getPremiseById(String id) {
        PremisesEntity c = premiseRepository.findOne(id);
        return c;
    }

    public PremisesEntity addPremise(PremisesEntity ce) {
        logger.info("=> Adding premise %s with id %s", ce.getDescription(), ce.getPremiseId());
        ce = premiseRepository.save(ce);
        return ce;
    }

    public void delPremise(String id) {
        PremisesEntity u = premiseRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting premise %s with id %s", u.getDescription(), u.getPremiseId());
            premiseRepository.delete(id);
        }
    }
}
