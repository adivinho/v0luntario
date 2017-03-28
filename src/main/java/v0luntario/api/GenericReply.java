package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 3/24/17.
 */
@XmlRootElement
public class GenericReply {
    @XmlElement(required=true)
    public Integer retcode = 0;
    @XmlElement(required=true)
    public String apiVer = "0.0.1";
    @XmlElement(required=false)
    public String error_message;
}
