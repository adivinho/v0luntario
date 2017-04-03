package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.ProductsEntity;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
public interface ProductRepository extends CrudRepository<ProductsEntity, String> {
    @Override
    public List<ProductsEntity> findAll();
}
