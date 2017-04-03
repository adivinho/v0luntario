package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import v0luntario.jpa.PremisesEntity;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
public interface PremiseRepository extends CrudRepository<PremisesEntity, String> {
    @Override
    public List<PremisesEntity> findAll();
}
