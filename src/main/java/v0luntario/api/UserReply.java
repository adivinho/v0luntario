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
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String lastName;
    @XmlElement(required=true)
    public String email;
    @XmlElement(required = true)
    public String role;
    @XmlElement(name="groups")
    public List<String> groups = new ArrayList<>();
}