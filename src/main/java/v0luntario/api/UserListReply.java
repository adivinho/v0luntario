package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/24/17.
 */
@XmlRootElement
public class UserListReply extends GenericReply{
    @XmlElement(required=true)
    public List<UserReply> users = new ArrayList<>();
}
