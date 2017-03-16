package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.UserdetailsEntity;

import java.util.List;

/**
 * Created by silvo on 3/16/17.
 */
public interface UserdetailsRepository extends CrudRepository<UserdetailsEntity, Long> {
    public List<UserdetailsEntity> findByFirstNameAndLastName(String firstName, String LastName);
}