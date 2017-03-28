package v0luntario.api;

import v0luntario.jpa.ProductsEntity;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by silvo on 3/26/17.
 */

@XmlRootElement
public class StashReply {
    @XmlElement(required=false)
    public ProductsEntity prod_id;
    @XmlElement(required=true)
    public UsersEntity user_id;
    @XmlElement(required=true)
    public BigDecimal amount;
    @XmlElement(required=true)
    public BigDecimal required_amount;
    @XmlElement(required=true)
    public String status;
    @XmlElement(required=true)
    public Timestamp deadline;
}
