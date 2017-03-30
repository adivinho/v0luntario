package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.GroupsEntity;

import java.util.List;

/**
 * Created by silvo on 3/29/17.
 */

public interface GroupRepository extends CrudRepository<GroupsEntity, String> {
    @Override
    public List<GroupsEntity> findAll();
}