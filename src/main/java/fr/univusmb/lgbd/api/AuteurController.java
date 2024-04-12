package fr.univusmb.lgbd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;

@Controller
@RequestMapping("/auteur")
public class AuteurController {

    @Autowired
    private PostgresAuteurDao auteurDao;

    @GetMapping("/all")
    public String getAll() {

        return auteurDao.getAll().toString();
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
