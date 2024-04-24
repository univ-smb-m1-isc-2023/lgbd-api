package fr.univusmb.lgbd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.model.Auteur;
import java.util.List;

@RestController
@RequestMapping("/auteur")
@CrossOrigin(origins = {"*"})
public class AuteurController {

    @Autowired
    private PostgresAuteurDao auteurDao;

    @GetMapping("/all")
    public ResponseEntity<List<Auteur>> get() {
        return ResponseEntity.ok(auteurDao.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<Auteur> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(auteurDao.get(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        System.out.println("CREATE : nom : " + nom + " prenom : " + prenom);
        auteurDao.save(new Auteur(nom, prenom));
        return ResponseEntity.ok().build();
    }

}
