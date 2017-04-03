package v0luntario.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.ProductsEntity;
import v0luntario.repository.ProductRepository;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
@Service
public class ProductService {
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;

    public List<ProductsEntity> getAllClasses(){
        return  productRepository.findAll();
    }

    public ProductsEntity getProductById(String id) {
        ProductsEntity pe = productRepository.findOne(id);
        return pe;
    }

    public ProductsEntity addProduct(ProductsEntity ce) {
        logger.info("=> Adding product %s with id %s", ce.getName(), ce.getClassId());
        ce = productRepository.save(ce);
        return ce;
    }

    public void delProduct(String id) {
        ProductsEntity u = productRepository.findOne(id);
        if (u != null) {
            logger.debug("=> Deleting product %s with id %s", u.getName(), u.getClassId());
            productRepository.delete(id);
        }
    }
}
