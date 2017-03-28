package v0luntario.jpa;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by silvo on 3/15/17.
 */
@Entity(name = "premises")
@Table(name = "premises")
@NamedQueries({
        @NamedQuery(name = "premises.findAll", query = "SELECT u FROM premises u")})
public class PremisesEntity {
    @Id
    @Column(name = "premise_id", nullable = false, length = 255)
    private String premiseId;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Basic
    @Column(name = "attachment", nullable = true)
    private byte[] attachment;
    @Basic
    @Column(name = "attachment2", nullable = true)
    private byte[] attachment2;
    @Basic
    @Column(name = "attachment3", nullable = true)
    private byte[] attachment3;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "premiseId")
    private Collection<MovementsEntity> movementCollection;

    public String getPremiseId() {
        return premiseId;
    }
    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttachment() {
        return attachment;
    }
    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public byte[] getAttachment2() {
        return attachment2;
    }
    public void setAttachment2(byte[] attachment2) {
        this.attachment2 = attachment2;
    }

    public byte[] getAttachment3() {
        return attachment3;
    }
    public void setAttachment3(byte[] attachment3) {
        this.attachment3 = attachment3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PremisesEntity that = (PremisesEntity) o;

        if (premiseId != null ? !premiseId.equals(that.premiseId) : that.premiseId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!Arrays.equals(attachment, that.attachment)) return false;
        if (!Arrays.equals(attachment2, that.attachment2)) return false;
        if (!Arrays.equals(attachment3, that.attachment3)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = premiseId != null ? premiseId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(attachment);
        result = 31 * result + Arrays.hashCode(attachment2);
        result = 31 * result + Arrays.hashCode(attachment3);
        return result;
    }
}