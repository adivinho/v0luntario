package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by silvo on 4/3/17.
 */
public class PremiseReply {
    @XmlElement(required=false)
    public String premise_id;
    @XmlElement(required=true)
    public String description;
}
