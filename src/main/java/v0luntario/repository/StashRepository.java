package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;

import java.util.List;

/**
 * Created by silvo on 3/26/17.
 */
public interface StashRepository extends CrudRepository<StashEntity, Long> {
    @Override
    public List<StashEntity> findAll();
}