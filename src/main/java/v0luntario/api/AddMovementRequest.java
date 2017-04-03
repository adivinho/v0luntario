package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by silvo on 4/3/17.
 */
public class AddMovementRequest {
    @XmlElement(required=true)
    public MovementReply movement;
}
