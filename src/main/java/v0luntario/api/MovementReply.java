package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 4/3/17.
 */
@XmlRootElement
public class MovementReply {
    @XmlElement(required=false)
    public String move_id;
    @XmlElement(required=true)
    public String prod_id;
    @XmlElement(required=true)
    public String user_id;
    @XmlElement(required=true)
    public String premise_id;
    @XmlElement(required=true)
    public String amount;
    @XmlElement(required=true)
    public String motion_date;
}
