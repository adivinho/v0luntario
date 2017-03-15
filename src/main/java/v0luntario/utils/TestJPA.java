package v0luntario.utils;


import com.sun.org.apache.xpath.internal.SourceTree;
import v0luntario.jpa.*;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by silvo on 3/14/17.
 */
public class TestJPA {

    public void usePersistence(){
        System.out.printf("=====> %s <=====\n",getClass().getSimpleName());
        try
        {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            EntityManager manager = factory.createEntityManager();

            EntityTransaction userTransaction = manager.getTransaction();
            userTransaction.begin();

            UsersEntity instance = new UsersEntity();
            instance.setUserId(String.valueOf(randomLong()));
            instance.setUsername("username"+randomShort());
            instance.setEmail("nnm"+randomShort()+"@testjpa.ru");
            instance.setPasswordHash(makeSHA1Hash("SuperStrongPassword"));
            long offset = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2017-03-01 00:00:00").getTime();
            long diff = end - offset + 1;
            Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
            instance.setLastLogin(rand);
            instance.setRole(UsersEntity.Roles.User);
            instance.setCreatedBy("1000");

            UserdetailsEntity instance_details = new UserdetailsEntity();
            instance_details.setUserId(instance.getUserId());
            instance_details.setPhone("321234123123");
            instance_details.setNotes(instance.getEmail());
            instance_details.setCountry("Ukraine");

            Calendar calendar = Calendar.getInstance();
            Timestamp dateNow = new java.sql.Timestamp(calendar.getTime().getTime());

            instance_details.setActivationDate(dateNow);

//            manager.persist(instance);
//            manager.persist(instance_details);
//            userTransaction.commit();

            TypedQuery<UsersEntity> res = manager.createNamedQuery("users.findAll",UsersEntity.class);
            System.out.println(res.getResultList());

            TypedQuery<UserdetailsEntity> res3 = manager.createNamedQuery("userdetails.findAll", UserdetailsEntity.class);
            System.out.println(res3.getResultList());

            TypedQuery<GroupsEntity> res4 = manager.createNamedQuery("groups.findAll", GroupsEntity.class);
            System.out.println(res4.getResultList());

            TypedQuery<ProductsEntity> res5 = manager.createNamedQuery("products.findAll", ProductsEntity.class);
            System.out.println(res5.getResultList());

            TypedQuery<UnitsEntity> res6 = manager.createNamedQuery("units.findAll", UnitsEntity.class);
            System.out.println(res6.getResultList());

            TypedQuery<ClassesEntity> res7 = manager.createNamedQuery("classes.findAll", ClassesEntity.class);
            System.out.println(res7.getResultList());

            TypedQuery<StashEntity> res8 = manager.createNamedQuery("stash.findAll", StashEntity.class);
            System.out.println(res8.getResultList());

            manager.close();
            factory.close();
            System.out.println("=====> Execução com sucesso!");
        }catch(Exception _Ex)
        {
            System.out.println("=====> Erro: " + _Ex.getMessage());
        }
    }

    public static void main(String[] args) {
        TestJPA tjpa = new TestJPA();
        tjpa.usePersistence();
    }

    public String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }

    public static Long randomLong(){
        return Math.abs((UUID.randomUUID().getLeastSignificantBits()));
    }

    public static Short randomShort(){
        return (short) Math.abs((UUID.randomUUID().getLeastSignificantBits()));
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
