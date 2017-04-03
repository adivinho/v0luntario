package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.UnitsEntity;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
public interface UnitRepository extends CrudRepository<UnitsEntity, String> {
    @Override
    public List<UnitsEntity> findAll();
}
