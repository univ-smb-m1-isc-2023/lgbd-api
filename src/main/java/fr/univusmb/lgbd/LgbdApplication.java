package fr.univusmb.lgbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class LgbdApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgbdApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping5("/bdd")
	public String bdd() {
		try{
			spring.datasource.url='jdbc:mysql://localhost:5432/bd_lgbd'
			spring.datasource.username='lgbd_root'
			spring.datasource.password='root_lgbd'
			return "Connexion à la base de données réussie !";
		}catch(Exception e){
			return "Connexion à la base de données échouée !";
		}
	}

}