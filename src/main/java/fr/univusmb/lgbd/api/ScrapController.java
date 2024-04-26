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

@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {
    private String body;
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

        // //Unescape JSON
        body = StringEscapeUtils.unescapeJava(body);

        // ObjectMapper mapper = new ObjectMapper();
        // JsonNode jsonBody = mapper.readTree(body);

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
        Long isbn = jsonBD.get("ISBN").asLong();
        String titre = jsonBD.get("titre").asText();
        String editeur = jsonBD.get("editeur").asText();
        Integer annee = jsonBD.get("annee").asInt();
        String resume = jsonBD.get("resume").asText();
        String nom = jsonBD.get("auteur").asText();
        String serie = jsonBD.get("serie").asText();

        System.out.println("Create : isbn : " + isbn);
        System.out.println("Create : titre : " + titre);
        System.out.println("Create : editeur : " + editeur);
        System.out.println("Create : annee : " + annee);
        System.out.println("Create : resume : " + resume);
        System.out.println("Create : nom : " + nom);
        System.out.println("Create : serie : " + serie);
    }
}