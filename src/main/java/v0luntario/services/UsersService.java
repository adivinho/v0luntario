package v0luntario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.UserdetailsEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.UserRepository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by silvo on 3/20/17.
 */
@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction userTransaction = manager.getTransaction();

    public List<UsersEntity> getAllUsers() throws NoResultException{
        Query query = manager.createQuery("Select c from users c");
        try {
            List<UsersEntity> result = query.getResultList();
            return result;
        }
        catch (NoResultException e){
            return null;
        }
//        return query.getResultList();
    };

    public UsersEntity getUserById(String id) throws NoResultException{
        Query query = manager.createQuery("Select c from users c where c.userId=:userId");
        query.setParameter("userId", id);
        try {
            return (UsersEntity) query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    };

    public UsersEntity getUserByUsername(String username) throws NoResultException{
        Query query = manager.createQuery("Select c from users c where c.username=:username");
        query.setParameter("username", username);
        try {
            return (UsersEntity) query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    };

    public void add_user(UsersEntity ue){
        userTransaction.begin();
        long offset = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2017-03-17 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        ue.setLastLogin(rand);
        manager.persist(ue);
        userTransaction.commit();
        //manager.close();
    }

    public void del_user(UsersEntity ue, UserdetailsEntity ud){
        userTransaction.begin();
        manager.remove(ud);
        userTransaction.commit();
        userTransaction.begin();
        manager.remove(ue);
        userTransaction.commit();
    }

    public void add_userdetails(UserdetailsEntity ude){
        userTransaction.begin();
        Calendar calendar = Calendar.getInstance();
        Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());
        ude.setActivationDate(dateNow);
        manager.persist(ude);
        userTransaction.commit();
        //manager.close();
    }

    public void edit_user(UsersEntity ue, UserdetailsEntity ud){
        userTransaction.begin();
        manager.merge(ue);
        manager.flush();
        userTransaction.commit();
        userTransaction.begin();
        manager.merge(ud);
        manager.flush();
        userTransaction.commit();
    }

}
