package fr.univusmb.lgbd.api;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresSerieDao;



@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {
    private String scrap;

    @Autowired
    private PostgresBdDao bdDao;
    private PostgresAuteurDao auteurDao;
    private PostgresSerieDao serieDao;

    @PostMapping("/scrap")
    public ResponseEntity<Void> scrap(@RequestBody String body) {
        System.out.println("Scrap : " + body);
        body = body.replace("")
        this.scrap = body;

        addBd(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scrap")
    public String getScrap() {
        return this.scrap;
    }

    public void addBd(String body) {
        // Convert body to JSON
        JacksonJsonParser parser = new JacksonJsonParser();
        parser.parseMap(body);
        Long isbn = Long.parseLong(parser.get("isbn"));
        String titre = parser.get("titre");
        String editeur = parser.get("editeur");
        Integer annee = Integer.parseInt(parser.get("annee"));
        String resume = parser.get("resume");
        String auteur = parser.get("auteur");
        String serie = parser.get("serie");
        System.out.println("CREATE : isbn : " + isbn);
        System.out.println("CREATE : titre : " + titre);
        System.out.println("CREATE : editeur : " + editeur);
        System.out.println("CREATE : annee : " + annee);
        System.out.println("CREATE : resume : " + resume);
        System.out.println("CREATE : auteur : " + auteur);
        System.out.println("CREATE : serie : " + serie);
        //System.out.println("CREATE : isbn : " + isbn + " title : " + titre + " editeur : " + editeur + " annee " + annee + " resume : " + resume + " auteur : " + auteur + " serie : " + serie);
        // Bd bd = new Bd(isbn, titre, editeur, annee, resume);
        // bdDao.save(bd);
        // Auteur auteur = auteurDao.getByNomPrenom(auteur);
        // if (auteur == null) {
        //     auteur = new Auteur(auteur);
        //     auteurDao.save(auteur);
        // }
        // Serie serie = serieDao.getByName(serie);
        // if (serie == null) {
        //     serie = new Serie(serie);
        //     serieDao.save(serie);
        // }
    }
}