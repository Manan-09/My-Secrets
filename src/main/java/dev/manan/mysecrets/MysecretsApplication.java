package dev.manan.mysecrets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MysecretsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysecretsApplication.class, args);
	}

}
