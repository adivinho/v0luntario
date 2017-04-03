package v0luntario.api;

import v0luntario.jpa.GroupsEntity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/24/17.
 */
@XmlRootElement
public class UserReply {
    @XmlElement(required=false)
    public String user_id;
    @XmlElement(required=true)
    public String login;
    @XmlElement(required=false)
    public String password;
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String lastName;
    @XmlElement(required=true)
    public String email;
    @XmlElement(required = false)
    public String createdBy;
    @XmlElement(required = true)
    public String role;
    @XmlElement(required = false)
    public String country;
    @XmlElement(required = false)
    public String city;
    @XmlElement(required = false)
    public String address;
    @XmlElement(required = false)
    public String phone;
    @XmlElement(required = false)
    public String midInit;
    @XmlElement(required = false)
    public String sex;
    @XmlElement(required = false)
    public String mobile;
    @XmlElement(required = false)
    public String mobile2;
    @XmlElement(required = false)
    public String notes;
    @XmlElement(required = false)
    public String activationDate;
    @XmlElement(name="groups")
    public List<String> groups = new ArrayList<>();
}