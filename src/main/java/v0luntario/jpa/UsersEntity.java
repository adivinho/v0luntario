package v0luntario.jpa;

import com.sun.istack.internal.NotNull;
import v0luntario.utils.EntityIdGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by silvo on 3/14/17.
 */
@Entity(name = "users")
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "users.findAll", query = "SELECT a FROM users a"),
        @NamedQuery(name = "users.getUserById", query = "SELECT a FROM users a")
})
public class UsersEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "user_id")
    private String userId;
    @Basic
    @NotNull
    @Column(name = "username")
    private String username;
    public enum Roles {
        Root,
        Admin,
        User,
        Watcher
    };
    @Basic
    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Basic
    @Column(name = "created_by")
    private String createdBy;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;
    @Basic
    @Column(name = "last_login")
    private Timestamp lastLogin;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private UserdetailsEntity userdetails;

    public void setUserdetails(UserdetailsEntity userdetails) {
        this.userdetails = userdetails;
    }

    public UserdetailsEntity getUserdetails() {
        return userdetails;
    }

    @JoinTable(name = "user_group",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
            }
    )

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<GroupsEntity> groupsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<MovementsEntity> movementCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stashId.userId")
    private Collection<StashEntity> stashCollection;


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }


    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.passwordHash = EntityIdGenerator.makeSHA1Hash(passwordHash);
    }


    public Timestamp getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UsersEntity() {
    }

    public UsersEntity(String userId) {
        this.userId = userId;
    }

    public UsersEntity(String userId, String username, String createdBy, String email, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.createdBy = createdBy;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public List<GroupsEntity> getGroupsList() {
        return groupsList;
    }
    public void setGroupsList(List<GroupsEntity> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id: " + getUserId() + ",\t usernamename: " + getUsername() + ",\t email: "+ getEmail() +",\t activation: "+userdetails.getActivationDate()+"\n";
    }
}