package v0luntario.api;

import v0luntario.jpa.ClassesEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by silvo on 4/3/17.
 */

@XmlRootElement
public class ProductReply {
    @XmlElement(required=true)
    public String product_id;
    @XmlElement(required=true)
    public String name;
    @XmlElement(required=false)
    public String description;
    @XmlElement(required = true)
    public String added_by;
    @XmlElement(required = true)
    public String class_id;
    @XmlElement(required = true)
    public String unit_id;
}