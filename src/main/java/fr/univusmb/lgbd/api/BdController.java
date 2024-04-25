package fr.univusmb.lgbd.api;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresAuteurDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresSerieDao;
import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import fr.univusmb.lgbd.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bd")
@CrossOrigin(origins = { "*" })
public class BdController {

    @Autowired
    private PostgresBdDao bdDao;
    private PostgresAuteurDao auteurDao;
    private PostgresSerieDao serieDao;

    @GetMapping("/all")
    public ResponseEntity<List<Bd>> get() {
        return ResponseEntity.ok(bdDao.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<Bd> get(@RequestParam("isbn") Long isbn) {
        Optional<Bd> search = bdDao.get(isbn);
        assert search.isPresent();
        Bd find = search.get();
        return ResponseEntity.ok(find);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestParam("isbn") Long isbn, @RequestParam("titre") String titre,
            @RequestParam("editeur") String editeur, @RequestParam("annee") Integer annee,
            @RequestParam("resume") String resume, @RequestParam("auteurNom") String auteurNom,
            @RequestParam("auteurPrenom") String auteurPrenom, @RequestParam("seriName") String serieName) {
        System.out
                .println("CREATE : isbn : " + isbn + " title : " + titre + " editeur : " + editeur + " annee " + annee);
        assert isbn != null;
        assert editeur != null;
        assert annee != null;
        assert titre != null;
        Auteur auteur = auteurDao.getByNomPrenom(auteurNom, auteurPrenom);
        if (auteur == null) {
            auteur = new Auteur(auteurNom, auteurPrenom);
            auteurDao.save(auteur);
        }
        Serie serie = serieDao.getByName(serieName);

        Integer note = 0;
        bdDao.save(new Bd(isbn, titre, editeur, annee, "", resume, note, auteur, "", serie));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestParam("isbn") Long isbn, @RequestParam("titre") String titre,
            @RequestParam("editeur") String editeur, @RequestParam("annee") Integer annee,
            @RequestParam("resume") String resume, @RequestParam("auteurNom") String auteurNom,
            @RequestParam("auteurPrenom") String auteurPrenom, @RequestParam("seriName") String serieName) {
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
            if (auteurNom != null && auteurPrenom != null) {
                Auteur auteur = auteurDao.getByNomPrenom(auteurNom, auteurPrenom);
                if (auteur == null) {
                    auteur = new Auteur(auteurNom, auteurPrenom);
                    auteurDao.save(auteur);
                }
                bdChange.setAuteur(auteur);
            }
            if (serieName != null) {
                Serie serie = serieDao.getByName(serieName);
                bdChange.setSerie(serie);
            }
            bdDao.update(bdChange);

            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
