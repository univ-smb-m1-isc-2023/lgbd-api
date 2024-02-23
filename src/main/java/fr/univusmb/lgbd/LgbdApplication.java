package fr.univusmb.lgbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LgbdApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgbdApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

}
