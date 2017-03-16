package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.UsersEntity;

import java.util.List;

/**
 * Created by silvo on 3/16/17.
 */
public interface UserRepository extends CrudRepository<UsersEntity, Long> {
        @Override
        public List<UsersEntity> findAll();
}
