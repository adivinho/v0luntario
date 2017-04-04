package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.StashReply;
import v0luntario.jpa.ProductsEntity;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.StashRepository;
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
 * Created by silvo on 3/26/17.
 */
@Component
public class StashMapper {
    private static final Logger logger =  LoggerFactory.getLogger(StashMapper.class);
    @Autowired
    StashRepository stashRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    public StashReply fromInternal(StashEntity se) {
        StashReply sr = null;
        if (se != null) {
            sr = new StashReply();
            sr.prod_id  = se.getProdId().toString();
            sr.user_id = se.getUserId().toString();
            sr.amount = se.getAmount().toString();
            sr.required_amount = se.getRequiredAmount().toString();
            sr.status = se.getStatus().toString();
            sr.deadline = se.getDeadline().toString();
        }
        return sr;
    }

    public StashEntity toInternal(StashReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        StashEntity au = null;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        if (lu.prod_id != null && lu.user_id != null) {
            logger.info("=> Provided %s stash_id ",lu.stash_id);
            au = stashRepository.findOne(lu.stash_id); //TODO rewrite
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new stash ...");
            au = newStash(lu);
        }
        else {
            logger.debug("=> Updating existing stash ...");
            ProductsEntity pe = productService.getProductById(lu.prod_id);
            au.setProdId(pe);
            UsersEntity ue = userService.getUserById(lu.user_id);
            au.setUserId(ue);
            String filteredString=lu.amount.replaceAll(",","");
            BigDecimal bd=new BigDecimal(filteredString);
            au.setAmount(bd);
            String filteredString2=lu.required_amount.replaceAll(",","");
            BigDecimal bd2=new BigDecimal(filteredString);
            au.setRequiredAmount(bd2);
            au.setStatus(Enum.valueOf(StashEntity.Status.class, lu.status));
            if (lu.deadline != null) {
                au.setDeadline(convertStringToTimestamp(lu.deadline));
            }
            else {
                Calendar calendar = Calendar.getInstance();
                Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
                au.setDeadline(dateNow);
            }
        }
        return au;
    }

    private StashEntity newStash(StashReply ur) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        StashEntity se = new StashEntity();

        if (ur.prod_id != null && ur.user_id != null) {
            ProductsEntity pe = productService.getProductById(ur.prod_id);
            se.setProdId(pe);
            UsersEntity ue = userService.getUserById(ur.user_id);
            se.setUserId(ue);
        }
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !stashRepository.exists(String.valueOf(id));
            }

        }
        String filteredString=ur.amount.replaceAll(",","");
        BigDecimal bd=new BigDecimal(filteredString);
        se.setAmount(bd);
        if (ur.deadline != null) {
            se.setDeadline(convertStringToTimestamp(ur.deadline));
        }
        else {
            Calendar calendar = Calendar.getInstance();
            Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
            se.setDeadline(dateNow);
        }

        if(ur.prod_id != null) {
            ProductsEntity pe = productService.getProductById(ur.prod_id);
            se.setProdId(pe);
        }

        if(ur.user_id != null) {
            UsersEntity usere = userService.getUserById(ur.user_id);
            se.setUserId(usere);
        }
        return se;
    }
}
