package v0luntario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class V0luntarioApplication {
	final static Logger logger = LoggerFactory.getLogger(V0luntarioApplication.class);
	public static void main(String[] args) {
		logger.info("=> Starting v0luntario application ...");
		SpringApplication.run(V0luntarioApplication.class, args);
	}
}
