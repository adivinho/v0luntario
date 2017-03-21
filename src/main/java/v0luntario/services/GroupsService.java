package v0luntario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.GroupRepository;
import v0luntario.repository.UserRepository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by silvo on 3/21/17.
 */

@Service
public class GroupsService {
    @Autowired
    GroupRepository groupRepository;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction userTransaction = manager.getTransaction();

    public List<GroupsEntity> getAllGroups() throws NoResultException {
        Query query = manager.createQuery("Select c from groups c");
        try {
            List<GroupsEntity> result = query.getResultList();
            return result;
        }
        catch (NoResultException e){
            return null;
        }
    };

    public GroupsEntity getGroupById(String id) throws NoResultException{
        Query query = manager.createQuery("Select c from groups c where c.groupId=:groupId");
        query.setParameter("groupId", id);
        try {
            return (GroupsEntity) query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    };

    public void add_group(GroupsEntity ge){
        userTransaction.begin();
        manager.persist(ge);
        userTransaction.commit();
        //manager.close();
    }

    public void del_group(GroupsEntity ge){
        userTransaction.begin();
        manager.remove(ge);
        userTransaction.commit();
    }

    public void edit_group(GroupsEntity ge){
        userTransaction.begin();
        manager.merge(ge);
        manager.flush();
        userTransaction.commit();
    }
}
