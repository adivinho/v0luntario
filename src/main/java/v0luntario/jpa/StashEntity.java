package v0luntario.jpa;

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
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    private ProductsEntity prodId;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity userId;
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
    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Basic
    @Column(name = "deadline", nullable = true)
    private Timestamp deadline;

    public ProductsEntity getProdId() {
        return prodId;
    }
    public void setProdId(ProductsEntity prodId) {
        this.prodId = prodId;
    }

    public UsersEntity getUserId() {
        return userId;
    }
    public void setUserId(UsersEntity userId) {
        this.userId = userId;
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

        if (prodId != null ? !prodId.equals(that.prodId) : that.prodId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (requiredAmount != null ? !requiredAmount.equals(that.requiredAmount) : that.requiredAmount != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prodId != null ? prodId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (requiredAmount != null ? requiredAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "prod_id: " + getProdId() + ",\t user_id: " + getUserId() + ",\t status: "+ getStatus()+"\t amount: " + getAmount() +"\n";
    }
}
