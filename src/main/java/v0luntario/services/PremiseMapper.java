package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.PremiseReply;
import v0luntario.jpa.PremisesEntity;
import v0luntario.repository.PremiseRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silvo on 4/3/17.
 */
@Component
public class PremiseMapper {
    private static final Logger logger =  LoggerFactory.getLogger(PremiseMapper.class);
    @Autowired
    PremiseRepository premiseRepository;

    public PremiseReply fromInternal(PremisesEntity ue) {
        PremiseReply ur = null;
        if (ue != null) {
            ur = new PremiseReply();
            ur.premise_id  = ue.getPremiseId();
            ur.description = ue.getDescription();
        }
        return ur;
    }

    public PremisesEntity toInternal(PremiseReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PremisesEntity au = null;
        if (lu.premise_id != null) {
            logger.info("=> Provided %s premise_id ",lu.premise_id);
            au = premiseRepository.findOne(lu.premise_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new premise ...");
            au = newPremise(lu);
        }
        else {
            logger.debug("=> Updating existing premise ...");
            au.setDescription(lu.description);
        }
        return au;
    }

    private PremisesEntity newPremise(PremiseReply ur) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PremisesEntity ue = new PremisesEntity();
        if (ur.premise_id != null) ue.setPremiseId(ur.premise_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !premiseRepository.exists(String.valueOf(id));
            }
            ue.setPremiseId(String.valueOf(id));
        }

        ue.setDescription(ur.description);
        return ue;
    }

}
