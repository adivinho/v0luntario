package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.MovementsEntity;
import v0luntario.repository.MovementRepository;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
@Service
public class MovementService {
    private static final Logger logger =  LoggerFactory.getLogger(MovementService.class);
    @Autowired
    MovementRepository movementRepository;

    public List<MovementsEntity> getAllMovements(){
        return  movementRepository.findAll();
    }

    public MovementsEntity getMovementById(String id) {
        MovementsEntity c = movementRepository.findOne(id);
        return c;
    }

    public MovementsEntity addMovement(MovementsEntity ce) {
        logger.info("=> Adding movement with id %s", ce.getMoveId());
        ce = movementRepository.save(ce);
        return ce;
    }

    public void delMovement(String id) {
        MovementsEntity u = movementRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting movement with id %s", u.getMoveId());
            movementRepository.delete(id);
        }
    }
}
