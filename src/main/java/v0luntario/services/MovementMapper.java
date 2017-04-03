package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.MovementReply;
import v0luntario.jpa.MovementsEntity;
import v0luntario.jpa.ProductsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.MovementRepository;
import v0luntario.repository.ProductRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

import static v0luntario.utils.EntityIdGenerator.convertStringToTimestamp;

/**
 * Created by silvo on 4/3/17.
 */
@Component
public class MovementMapper {
    private static final Logger logger =  LoggerFactory.getLogger(MovementMapper.class);
    @Autowired
    MovementRepository movementRepository;

    public MovementReply fromInternal(MovementsEntity ue) {
        MovementReply ur = null;
        if (ue != null) {
            ur = new MovementReply();
            ur.move_id  = ue.getMoveId();
            ur.prod_id = ue.getProdId().toString();
            ur.premise_id = ue.getPremiseId().toString();
            ur.user_id = ue.getUserId().toString();
            ur.amount = ue.getAmount().toString();
            ur.motion_date = ue.getMotionDate().toString();
        }
        return ur;
    }

    public MovementsEntity toInternal(MovementReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        MovementsEntity au = null;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        if (lu.move_id != null) {
            logger.info("=> Provided %s move_id ",lu.move_id);
            au = movementRepository.findOne(lu.move_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new movement ...");
            au = newMovement(lu);
        }
        else {
            logger.debug("=> Updating existing move ...");
            String filteredString=lu.amount.replaceAll(",","");
            BigDecimal bd=new BigDecimal(filteredString);
            au.setAmount(bd);
            if (lu.motion_date != null) {
                au.setMotionDate(convertStringToTimestamp(lu.motion_date));
            }
                else {
                Calendar calendar = Calendar.getInstance();
                Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
                au.setMotionDate(dateNow);
            }
        }
        return au;
    }

    private MovementsEntity newMovement(MovementReply ur) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MovementsEntity ue = new MovementsEntity();
//        ProductService productService = new ProductService();
//        UserService userService = new UserService();

        if (ur.move_id != null) ue.setMoveId(ur.move_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !movementRepository.exists(String.valueOf(id));
            }
            ue.setMoveId(String.valueOf(id));
        }
        String filteredString=ur.amount.replaceAll(",","");
        BigDecimal bd=new BigDecimal(filteredString);
        ue.setAmount(bd);
        if (ur.motion_date != null) {
            ue.setMotionDate(convertStringToTimestamp(ur.motion_date));
        }
        else {
            Calendar calendar = Calendar.getInstance();
            Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
            ue.setMotionDate(dateNow);
        }

//        if(ur.prod_id != null) {
//            ProductsEntity pe = productService.getProductById(ur.prod_id);
//            ue.setProdId(pe);
//        }

//        if(ur.user_id != null) {
//            UsersEntity usere = userService.getUserById(ur.user_id);
//            ue.setUserId(usere);
//        }

        return ue;
    }
}
