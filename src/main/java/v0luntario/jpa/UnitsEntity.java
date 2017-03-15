package v0luntario.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "units")
@Table(name = "units")
@NamedQueries({
        @NamedQuery(name = "units.findAll", query = "SELECT u FROM units u")})
public class UnitsEntity implements Serializable {
    @Id
    @Column(name = "unit_id", nullable = false, length = 255)
    private String unitId;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitId")
    private Collection<ProductsEntity> productsCollection;

    public String getUnitId() {
        return unitId;
    }
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitsEntity that = (UnitsEntity) o;

        if (unitId != null ? !unitId.equals(that.unitId) : that.unitId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = unitId != null ? unitId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "unit id: " + getUnitId() + ",\t unit name: " + getName() + "\t Members: "+productsCollection.size()+"\n";
    }
}
