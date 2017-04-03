package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 4/2/17.
 */
@XmlRootElement
public class ClassReply {
    @XmlElement(required=true)
    public String class_id;
    @XmlElement(required=false)
    public String name;
    @XmlElement(required=false)
    public String description;
}
