package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.UnitReply;
import v0luntario.jpa.UnitsEntity;
import v0luntario.repository.UnitRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silvo on 4/3/17.
 */
@Component
public class UnitMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UnitMapper.class);
    @Autowired
    UnitRepository unitRepository;

    public UnitReply fromInternal(UnitsEntity ue) {
        UnitReply ur = null;
        if (ue != null) {
            ur = new UnitReply();
            ur.unit_id  = ue.getUnitId();
            ur.name = ue.getName();
        }
        return ur;
    }

    public UnitsEntity toInternal(UnitReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UnitsEntity au = null;
        if (lu.unit_id != null) {
            logger.info("=> Provided %s unit_id ",lu.unit_id);
            au = unitRepository.findOne(lu.unit_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new unit ...");
            au = newUnit(lu);
        }
        else {
            logger.debug("=> Updating existing unit ...");
            au.setName(lu.name);
        }
        return au;
    }

    private UnitsEntity newUnit(UnitReply ur) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UnitsEntity ue = new UnitsEntity();
        if (ur.unit_id != null) ue.setUnitId(ur.unit_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !unitRepository.exists(String.valueOf(id));
            }
            ue.setUnitId(String.valueOf(id));
        }

        ue.setName(ur.name);
        return ue;
    }
}
