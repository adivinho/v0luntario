package v0luntario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import v0luntario.api.StashReply;
import v0luntario.jpa.StashEntity;
import v0luntario.repository.StashRepository;

/**
 * Created by silvo on 3/26/17.
 */
@Component
public class StashMapper {
    @Autowired
    StashRepository stashRepository;

    public StashReply fromInternal(StashEntity se) {
        StashReply sr = null;
        if (se != null) {
            sr = new StashReply();
            sr.prod_id  = se.getProdId().toString();
            sr.user_id = se.getUserId().toString();
            sr.amount = se.getAmount();
            sr.required_amount = se.getRequiredAmount();
            sr.status = se.getStatus().toString();
            sr.deadline = se.getDeadline();
        }
        return sr;
    }
}
