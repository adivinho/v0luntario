package v0luntario.api;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvo on 4/2/17.
 */
public class ClassListReply extends GenericReply{
        @XmlElement(required=true)
        public List<ClassReply> classes = new ArrayList<>();
}
