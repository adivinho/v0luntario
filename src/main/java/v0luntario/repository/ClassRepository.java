package v0luntario.repository;

import org.springframework.data.repository.CrudRepository;
import v0luntario.jpa.ClassesEntity;

import java.util.List;

/**
 * Created by silvo on 4/2/17.
 */
public interface ClassRepository extends CrudRepository<ClassesEntity, String> {
    @Override
    public List<ClassesEntity> findAll();
}