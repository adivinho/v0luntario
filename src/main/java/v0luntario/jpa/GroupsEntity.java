package v0luntario.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/24/17.
 */
@Entity(name = "groups")
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(name = "groups.findAll", query = "SELECT u FROM groups u")})
public class GroupsEntity {
    @Id
    @Column(name = "group_id")
    private String groupId;
    @Basic
    @Column(name = "group_name")
    private String groupName;
    @Basic
    @Column(name = "description")
    private String description;

    public List<UsersEntity> getUsersList() {
        return usersList;
    }
    public void setUsersList(List<UsersEntity> usersList) {
        this.usersList = usersList;
    }

    @ManyToMany(mappedBy = "groupsList", cascade = CascadeType.DETACH)
    private List<UsersEntity> usersList = new ArrayList<>();

    public GroupsEntity() {}
    public GroupsEntity(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "group id: " + getGroupId() + ",\t group name: " + getGroupName() + "\t Members: "+usersList.size()+"\n";
    }
}
