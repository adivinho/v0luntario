package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 3/27/17.
 */
@XmlRootElement
public class AddUserRequest {
    @XmlElement(required=true)
    public UserReply user;
}