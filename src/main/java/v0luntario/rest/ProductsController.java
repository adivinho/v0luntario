package v0luntario.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import v0luntario.api.AddProductRequest;
import v0luntario.api.GenericReply;
import v0luntario.api.ProductListReply;
import v0luntario.jpa.ProductsEntity;
import v0luntario.services.ProductMapper;
import v0luntario.services.ProductService;

import java.io.EOFException;

/**
 * Created by silvo on 4/3/17.
 */
@RestController
@EnableAutoConfiguration
public class ProductsController {
    private static final Logger logger =  LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    @RequestMapping(path="/product/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductListReply getAllProducts(){
        ProductListReply reply = new ProductListReply();
        for(ProductsEntity se: productService.getAllClasses()){
            reply.products.add(productMapper.fromInternal(se));
        }
        return reply;
    }

    @RequestMapping(path="/product/byid/{productid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductListReply getClassById(@PathVariable String productid ){
        logger.info("=> /product/byid request has come for id: "+productid);
        ProductListReply reply = new ProductListReply();
        reply.products.add(productMapper.fromInternal(productService.getProductById(productid)));
        return reply;
    }

    @RequestMapping(path="/product/add",  method= {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductListReply addClass(@RequestBody AddProductRequest req) throws EOFException {
        logger.info("=> /product/add request has come");
        ProductListReply rep = new ProductListReply();
        try{
            ProductsEntity ue = productService.addProduct(productMapper.toInternal(req.product));
            rep.products.add(productMapper.fromInternal(ue));
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("=> Error adding a product. Exception: "+e.getMessage());
        }
        return rep;
    }

    @RequestMapping(path="/product/del/{productid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delProduct(@PathVariable String productid ){
        GenericReply rep = new GenericReply();
        try{
            productService.delProduct(productid);
        }
        catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error dropping a product. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }
}
