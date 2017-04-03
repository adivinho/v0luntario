package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by silvo on 4/2/17.
 */
public class AddStashRequest {
    @XmlElement(required=true)
    public StashReply stash;
}
