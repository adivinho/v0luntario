package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.ProductReply;
import v0luntario.jpa.ClassesEntity;
import v0luntario.jpa.ProductsEntity;
import v0luntario.jpa.UnitsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.ProductRepository;
import v0luntario.utils.EntityIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silvo on 4/3/17.
 */
@Component
public class ProductMapper {
    private static final Logger logger =  LoggerFactory.getLogger(ProductMapper.class);
    @Autowired
    ProductRepository productRepository;

    public ProductReply fromInternal(ProductsEntity pe) {
        ProductReply pr = null;
        if (pe != null) {
            pr = new ProductReply();
            pr.product_id  = pe.getProdId();
            pr.name = pe.getName();
            pr.description = pe.getDescription();
            pr.added_by = pe.getAdded_by().toString();
            pr.class_id = pe.getClassId().toString();
            pr.unit_id = pe.getUnitId().toString();
        }
        return pr;
    }

    public ProductsEntity toInternal(ProductReply lu) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ProductsEntity au = null;
        if (lu.product_id != null) {
            logger.info("=> Provided %s product_id ",lu.product_id);
            au = productRepository.findOne(lu.class_id);
        }
        if (au == null) { //not found, create new
            logger.debug("=> Creating a new product ...");
            au = newProduct(lu);
        }
        else {
            logger.debug("=> Updating existing product ...");
            au.setName(lu.name);
            au.setDescription(lu.description);
        }
        return au;
    }

    private ProductsEntity newProduct(ProductReply pr) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ProductsEntity pe = new ProductsEntity();
        if (pr.product_id != null) pe.setProdId(pr.product_id);
        else {
            boolean idOK = false;
            Long id = 0L;
            while (!idOK) {
                id = EntityIdGenerator.random();
                logger.debug("=> Generated new ID:" + id);
                idOK = !productRepository.exists(String.valueOf(id));
            }
            pe.setProdId(String.valueOf(id));
        }

        pe.setName(pr.name);
        pe.setDescription(pr.description);

        ClassesEntity ce = new ClassesEntity();
        ce.setClassId(pr.class_id);
        pe.setClassId(ce);

        UnitsEntity ue = new UnitsEntity();
        ue.setUnitId(pr.unit_id);
        pe.setUnitId(ue);

        UsersEntity use = new UsersEntity();
        use.setUserId(pr.added_by);
        pe.setAdded_by(use);

        return pe;
    }
}
