package v0luntario.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "products")
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "products.findAll", query = "SELECT a FROM products a")
})
public class ProductsEntity implements Serializable {
    @Id
    @Column(name = "prod_id", nullable = false, length = 255)
    private String prodId;
    @Basic
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
    private UnitsEntity unitId;

    public UnitsEntity getUnitId() {
        return unitId;
    }

    public void setUnitId(UnitsEntity unitId) {
        this.unitId = unitId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private ClassesEntity classId;

    public ClassesEntity getClassId() {
        return classId;
    }

    public void setClassId(ClassesEntity classId) {
        this.classId = classId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private Collection<MovementsEntity> movementCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stashId.prodId")
    private Collection<StashEntity> stashCollection;


    @ManyToOne(optional = false)
    @JoinColumn(name = "added_by", referencedColumnName = "user_id")
    private UsersEntity added_by;

//    @Basic
//    @Column(name = "added_by", nullable = true, length = 255)
//    private String added_by;

    public UsersEntity getAdded_by() {
        return added_by;
    }

    public void setAdded_by(UsersEntity added_by) {
        this.added_by = added_by;
    }

    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
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

        ProductsEntity that = (ProductsEntity) o;

        if (prodId != null ? !prodId.equals(that.prodId) : that.prodId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prodId != null ? prodId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id: " + getProdId() + ",\t name: " + getName() + ",\t description: "+ getDescription()+"\n";
//        return "22";
    }
}
