package fr.univusmb.lgbd.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresSerieDao;
import java.util.HashMap;
import java.util.Map;
import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import fr.univusmb.lgbd.model.Serie;
import java.util.Optional;


@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {
    private String scrap;
    private JsonNode map;

    @Autowired
    private PostgresBdDao bdDao;
    private PostgresAuteurDao auteurDao;
    private PostgresSerieDao serieDao;

    @PostMapping("/scrap")
    public ResponseEntity<Void> scrap(@RequestBody String body) throws Exception{
        System.out.println("Scrap : " + body);
        body = body.replace("\\\\", "\u0000"); // Temporarily replace \\ with a placeholder
        body = body.replace("\\", ""); // Remove \
        body = body.replace("\u0000", "\\"); // Replace placeholder with \\
        body = body.replace("/", "\\/");

        System.out.println("Scrap1 : " + body);

        body = body.replaceFirst("\"", ""); // Remove first "
        body = body.substring(0, body.length() - 1); // Remove last "

        System.out.println("Scrap2 : " + body);


        // //Unescape JSON
        body = StringEscapeUtils.unescapeJava(body);

        System.out.println("Scrap3 : " + body);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonBody = mapper.readTree(body);

        System.out.println("Scrap4 : " + jsonBody);

        this.scrap = body;
        this.map = jsonBody;

        addBd(jsonBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scrap")
    public String getScrap() {
        return this.scrap;
    }

    @GetMapping("/scrap/map")
    public JsonNode getMap() {
        return this.map;
    }

    public void addBd(JsonNode jsonBD) {
        String isbn = jsonBD.get("ISBN").asText();
        String titre = jsonBD.get("titre").asText();
        String editeur = jsonBD.get("editeur").asText();
        Integer annee = jsonBD.get("annee").asInt();
        String resume = jsonBD.get("resume").asText();
        String nom = jsonBD.get("auteur").asText();
        String serie = jsonBD.get("album").asText();

        System.out.println("Create : isbn : " + isbn);
        System.out.println("Create : titre : " + titre);
        System.out.println("Create : editeur : " + editeur);
        System.out.println("Create : annee : " + annee);
        System.out.println("Create : resume : " + resume);
        System.out.println("Create : nom : " + nom);
        System.out.println("Create : serie : " + serie);

        //Ajout à la base de données
        Optional<Auteur> search = auteurDao.getByNomPrenom(nom);
        Auteur auteur = null;
        if(search.isPresent()){
            auteur = search.get();
        } else {
            auteur = new Auteur(nom);
            auteurDao.save(auteur);
        }
        Bd bd = new Bd(Long.parseLong(isbn), titre, editeur, 2024,null, resume, 0, auteur, null, serie);
        bdDao.save(bd);
    
    }
}