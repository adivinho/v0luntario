package v0luntario.jpa;

import javafx.util.converter.TimeStringConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by silvo on 3/14/17.
 */
@Entity(name="userdetails")
@Table(name = "userdetails")
@NamedQueries({
        @NamedQuery(name = "userdetails.findAll", query = "SELECT u FROM userdetails u")})
public class UserdetailsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "mid_init")
    private String midInit;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "mobile")
    private String mobile;
    @Basic
    @Column(name = "mobile2")
    private String mobile2;
    @Basic
    @Column(name = "notes")
    private String notes;
    @Basic
    @Column(name = "activation_date")
    private Timestamp activationDate;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UsersEntity users;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getMidInit() {
        return midInit;
    }
    public void setMidInit(String midInit) {
        this.midInit = midInit;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getMobile2() {
        return mobile2;
    }
    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }


    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Timestamp getActivationDate() {
        return activationDate;
    }
    public void setActivationDate(Timestamp activationDate) {
        this.activationDate = activationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserdetailsEntity that = (UserdetailsEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (midInit != null ? !midInit.equals(that.midInit) : that.midInit != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (mobile2 != null ? !mobile2.equals(that.mobile2) : that.mobile2 != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (activationDate != null ? !activationDate.equals(that.activationDate) : that.activationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (midInit != null ? midInit.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (mobile2 != null ? mobile2.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (activationDate != null ? activationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id: " + getUserId() + ",\t activated at: "+ getActivationDate() +"\n";
    }
}
