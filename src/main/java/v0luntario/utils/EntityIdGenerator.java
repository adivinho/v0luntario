package v0luntario.utils;

import java.util.UUID;

/**
 * Created by silvo on 3/14/17.
 */
public class EntityIdGenerator {
    public static Long random(){
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
