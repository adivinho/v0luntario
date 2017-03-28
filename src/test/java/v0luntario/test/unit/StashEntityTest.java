package v0luntario.test.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import v0luntario.services.StashService;
import v0luntario.services.UserService;

/**
 * Created by silvo on 3/26/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StashEntityTest {
        @Autowired
        private StashService ss;

        @Test
        public void checkStashTest() throws Exception {
            int count = ss.getAllStash().size();
            assert(count>=3);
        }
}
