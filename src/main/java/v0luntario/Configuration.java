package v0luntario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by silvo on 3/25/17.
 */

@org.springframework.context.annotation.Configuration
@EnableWebMvc
public class Configuration extends WebMvcConfigurerAdapter {
    private static final Logger logger =  LoggerFactory.getLogger(Configuration.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.warn("=================== Addirng CORS requests support for /** =====================");
        registry.addMapping("/**");
    }

}