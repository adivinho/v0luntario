package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 4/3/17.
 */
@XmlRootElement
public class UnitReply {
    @XmlElement(required=false)
    public String unit_id;
    @XmlElement(required=true)
    public String name;
}
