package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 3/26/17.
 */

@XmlRootElement
public class StashListReply extends GenericReply{
    @XmlElement(required=true)
    public List<StashReply> stash = new ArrayList<>();
}