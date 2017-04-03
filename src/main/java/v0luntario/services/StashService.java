package v0luntario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v0luntario.jpa.StashEntity;
import v0luntario.jpa.UsersEntity;
import v0luntario.repository.StashRepository;


import java.util.List;

/**
 * Created by silvo on 3/26/17.
 */
@Service
public class StashService {
    @Autowired
    StashRepository stashRepository;

    public List<StashEntity> getAllStash(){
        return  stashRepository.findAll();
    }

    public StashEntity getStashByUserId(String id) {
        StashEntity s = stashRepository.findOne(id);
        return s;
    }
}
