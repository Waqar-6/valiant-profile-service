package com.wfarooq.profile_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Profile microservice Documentation",
				description = "Valiant Paws Microservice Rest Api",
				version = "V1",
				contact = @Contact(
						name = "Waqar Farooq",
						email = "farooqwaqar18@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class ProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

}
