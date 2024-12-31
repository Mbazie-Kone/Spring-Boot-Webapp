package it.mbaziekone.spring-boot-webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "it.mbaziekone.spring-boot-webapp")
public class SpringBootWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebappApplication.class, args);
	}
}