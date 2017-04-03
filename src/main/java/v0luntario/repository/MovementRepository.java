package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.MovementsEntity;

import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
public interface MovementRepository extends CrudRepository<MovementsEntity, String> {
    @Override
    public List<MovementsEntity> findAll();
}