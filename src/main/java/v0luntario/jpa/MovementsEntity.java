package v0luntario.jpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "movements")
@Table(name = "movements")
public class MovementsEntity {
    @Id
    @Column(name = "move_id", nullable = false, length = 255)
    private String moveId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    private ProductsEntity prodId;

    public ProductsEntity getProdId() {
        return prodId;
    }
    public void setProdId(ProductsEntity prodId) {
        this.prodId = prodId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity userId;

    public UsersEntity getUserId() {
        return userId;
    }
    public void setUserId(UsersEntity userId) {
        this.userId = userId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "premise_id", referencedColumnName = "premise_id")
    private PremisesEntity premiseId;

    public PremisesEntity getPremiseId() {
        return premiseId;
    }
    public void setPremiseId(PremisesEntity premiseId) {
        this.premiseId = premiseId;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "motion_date", nullable = true)
    private Timestamp motionDate;

    public String getMoveId() {
        return moveId;
    }
    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getMotionDate() {
        return motionDate;
    }
    public void setMotionDate(Timestamp motionDate) {
        this.motionDate = motionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovementsEntity that = (MovementsEntity) o;

        if (moveId != null ? !moveId.equals(that.moveId) : that.moveId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (motionDate != null ? !motionDate.equals(that.motionDate) : that.motionDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moveId != null ? moveId.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (motionDate != null ? motionDate.hashCode() : 0);
        return result;
    }
}
