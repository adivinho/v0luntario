package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 3/29/17.
 */
@XmlRootElement
public class AddGroupRequest {
        @XmlElement(required=true)
        public GroupReply group;
}
