package v0luntario.jpa;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "stash")
@Table(name = "stash")
@NamedQueries({
        @NamedQuery(name = "stash.findAll", query = "SELECT a FROM stash a")
})
public class StashEntity {

    @EmbeddedId StashId stashId;

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "required_amount", nullable = true, precision = 2)
    private BigDecimal requiredAmount;
    public enum Status {
        Active,
        Suspended,
        Closed
    };
    @Basic
    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Basic
    @Column(name = "deadline", nullable = true)
    private Timestamp deadline;

    public ProductsEntity getProdId() {
        return stashId.prodId;
    }
    public void setProdId(ProductsEntity prodId) {
        this.stashId.prodId = prodId;
    }

    public UsersEntity getUserId() {
        return stashId.userId;
    }
    public void setUserId(UsersEntity userId) {
        this.stashId.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRequiredAmount() {
        return requiredAmount;
    }
    public void setRequiredAmount(BigDecimal requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getDeadline() {
        return deadline;
    }
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StashEntity that = (StashEntity) o;

        if (stashId.prodId != null ? !stashId.prodId.equals(that.stashId.prodId) : that.stashId.prodId != null) return false;
        if (stashId.userId != null ? !stashId.userId.equals(that.stashId.userId) : that.stashId.userId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (requiredAmount != null ? !requiredAmount.equals(that.requiredAmount) : that.requiredAmount != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stashId.prodId != null ? stashId.prodId.hashCode() : 0;
        result = 31 * result + (stashId.userId != null ? stashId.userId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (requiredAmount != null ? requiredAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
//        return "prod_id: " + getProdId() + ",\t user_id: " + getUserId() + ",\t status: "+ getStatus()+"\t amount: " + getAmount() +"\n";
        return "12";
    }

};

@Embeddable
class StashId implements Serializable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    public ProductsEntity prodId;
//    public String prodId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public UsersEntity userId;
//    public String userId;
}
