package fr.univusmb.lgbd.api;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresSerieDao;
import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import fr.univusmb.lgbd.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bd")
@CrossOrigin(origins = { "*" })
public class BdController {

    @Autowired
    private PostgresBdDao bdDao;
    @Autowired
    private PostgresAuteurDao auteurDao;
    @Autowired
    private PostgresSerieDao serieDao;

    @GetMapping("/all")
    public ResponseEntity<List<Bd>> get() {
        return ResponseEntity.ok(bdDao.getAll());
    }

    @GetMapping("/bdCount")
    public ResponseEntity<Long> count() {
        long count = bdDao.getAll().size();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/get")
    public ResponseEntity<Bd> get(@RequestParam("isbn") Long isbn) {
        System.out.println("GET : isbn : " + isbn);
        Optional<Bd> search = bdDao.get(isbn);
        assert search.isPresent();
        Bd find = search.get();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestParam("isbn") Long isbn,
            @RequestParam(required = false, name = "titre") String titre,
            @RequestParam(required = false, name = "editeur") String editeur,
            @RequestParam(required = false, name = "annee") Integer annee,
            @RequestParam(required = false, name = "resume") String resume,
            @RequestParam(required = false, name = "auteurNom") String auteurNom,
            @RequestParam(required = false, name = "auteurPrenom") String auteurPrenom,
            @RequestParam(required = false, name = "seriName") String serieName,
            @RequestParam(required = false, name = "note") List<String> genre,
            @RequestParam(required = false, name = "image") List<String> image) {
        System.out
                .println("CREATE : isbn : " + isbn + " title : " + titre + " editeur : " + editeur + " annee " + annee);
        assert isbn != null;
        assert editeur != null;
        assert annee != null;
        assert titre != null;

        Auteur auteur = auteurDao.getByNomPrenom(auteurNom);
        if (auteur == null) {
            System.out.println("Auteur not found");
            auteur = new Auteur(auteurNom);
            auteurDao.save(auteur);
        }
        Serie serie = serieDao.getByName(serieName);

        Integer note = 0;
        System.out.println("Auteur = " + auteur.getNom());
        bdDao.save(new Bd(isbn, titre, editeur, annee, genre, resume, note, auteur, image, serie));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestParam("isbn") Long isbn,
            @RequestParam(required = false, name = "titre") String titre,
            @RequestParam(required = false, name = "editeur") String editeur,
            @RequestParam(required = false, name = "annee") Integer annee,
            @RequestParam(required = false, name = "resume") String resume,
            @RequestParam(required = false, name = "auteur") String auteurNom,
            @RequestParam(required = false, name = "serie") String serieName,
            @RequestParam(required = false, name = "note") List<String> genre,
            @RequestParam(required = false, name = "image") List<String> image) {
        System.out
                .println("UPDATE : isbn : " + isbn + " title : " + titre + " editeur : " + editeur + " annee " + annee);
        assert isbn != null;
        Optional<Bd> bd = bdDao.get(isbn);
        if (bd.isPresent()) {
            Bd bdChange = bd.get();
            if (editeur != null) {
                bdChange.setEditeur(editeur);
            }
            if (annee != null) {
                bdChange.setAnnee(annee);
            }
            if (titre != null) {
                bdChange.setTitre(titre);
            }
            if (resume != null) {
                bdChange.setResume(resume);
            }
            if (auteurNom != null) {
                Auteur auteur = auteurDao.getByNomPrenom(auteurNom);
                if (auteur == null) {
                    auteur = new Auteur(auteurNom);
                    auteurDao.save(auteur);
                }
                bdChange.setAuteur(auteur);
            }
            if (serieName != null) {
                Serie serie = serieDao.getByName(serieName);
                bdChange.setSerie(serie);
            }
            if (image != null) {
                bdChange.setImage(image);
            }
            if (genre != null) {
                bdChange.setGenre(genre);
            }

            bdDao.update(bdChange);

            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
