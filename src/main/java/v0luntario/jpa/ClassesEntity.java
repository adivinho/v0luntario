package v0luntario.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "classes")
@Table(name = "classes")
@NamedQueries({
        @NamedQuery(name = "classes.findAll", query = "SELECT u FROM classes u")})
public class ClassesEntity implements Serializable {
    @Id
    @Column(name = "class_id", nullable = false, length = 255)
    private String classId;
    @Basic
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<ProductsEntity> productsCollection;

    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

        ClassesEntity that = (ClassesEntity) o;

        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "class id: " + getClassId() + ",\t class name: " + getName() + "\t Members: "+productsCollection.size()+"\n";
    }
}
