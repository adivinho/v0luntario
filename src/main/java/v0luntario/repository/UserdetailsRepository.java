package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.UserdetailsEntity;

import java.util.List;

/**
 * Created by silvo on 3/28/17.
 */
public interface UserdetailsRepository extends CrudRepository<UserdetailsEntity, String> {
    public List<UserdetailsEntity> findByFirstNameAndLastName(String firstName, String LastName);
}