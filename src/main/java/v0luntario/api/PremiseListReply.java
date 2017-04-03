package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 4/3/17.
 */
public class PremiseListReply extends GenericReply{
    @XmlElement(required=true)
    public List<PremiseReply> premises = new ArrayList<>();
}
