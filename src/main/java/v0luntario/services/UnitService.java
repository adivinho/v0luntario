package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.UnitsEntity;
import v0luntario.repository.UnitRepository;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
@Service
public class UnitService {
    private static final Logger logger =  LoggerFactory.getLogger(UnitService.class);
    @Autowired
    UnitRepository unitRepository;

    public List<UnitsEntity> getAllUnits(){
        return  unitRepository.findAll();
    }

    public UnitsEntity getUnitById(String id) {
        UnitsEntity c = unitRepository.findOne(id);
        return c;
    }

    public UnitsEntity addUnit(UnitsEntity ce) {
        logger.info("=> Adding unit %s with id %s", ce.getName(), ce.getUnitId());
        ce = unitRepository.save(ce);
        return ce;
    }

    public void delUnit(String id) {
        UnitsEntity u = unitRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting unit %s with id %s", u.getName(), u.getUnitId());
            unitRepository.delete(id);
        }
    }
}
