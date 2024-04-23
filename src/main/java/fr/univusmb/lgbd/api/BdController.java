package fr.univusmb.lgbd.api;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bd")
public class BdController {

    @Autowired
    private PostgresBdDao bdDao;

    @GetMapping("/all")
    public ResponseEntity<List<Bd>> get() {
        return ResponseEntity.ok(bdDao.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<Bd> get(@RequestParam("id") Long id) {
        Optional<Bd> search = bdDao.get(id);
        assert search.isPresent();
        Bd find = search.get();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestParam("isbn") Long isbn, @RequestParam("titre") String titre, @RequestParam("editeur") String editeur, @RequestParam("annee") Integer annee) {
        System.out.println("CREATE : nom : " + isbn + " prenom : " + titre + " editeur : " + editeur + " annee " + annee);
        assert isbn != null;
        bdDao.save(new Bd(isbn, titre,editeur,annee));
        return ResponseEntity.ok().build();
    }
}
