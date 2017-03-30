package v0luntario.api;

import v0luntario.jpa.GroupsEntity;
import v0luntario.jpa.UsersEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/29/17.
 */
@XmlRootElement
public class GroupReply {
    @XmlElement(required=false)
    public String group_id;
    @XmlElement(required=true)
    public String group_name;
    @XmlElement(required=true)
    public String description;
    @XmlElement(name="users")
    public List<String> users = new ArrayList<>();
}