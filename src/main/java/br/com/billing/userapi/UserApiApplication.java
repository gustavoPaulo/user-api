package br.com.billing.userapi;

import br.com.billing.userapi.config.property.UserApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(UserApiProperty.class)
public class UserApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

}
