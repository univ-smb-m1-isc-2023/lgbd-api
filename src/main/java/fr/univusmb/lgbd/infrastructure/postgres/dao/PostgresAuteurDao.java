package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostgresAuteurJPA;
import fr.univusmb.lgbd.model.Auteur;
import java.util.List;

@Repository
public class PostgresAuteurDao implements Dao<Auteur> {

    @Autowired
    private PostgresAuteurJPA auteurJPA;
    private Long nextId = 1L;

    @Override
    public Auteur save(Auteur element) {
        assert element.getId() == null;
        Auteur newAuteur = new Auteur(nextId++, element.getNom());

        return auteurJPA.save(newAuteur);
    }

    @Override
    public Optional<Auteur> get(Long id) {
        return auteurJPA.findById(id);
    }

    @Override
    public List<Auteur> getAll() {
        return auteurJPA.findAll();
    }

    public Auteur getByNomPrenom(String nom) {
        List<Auteur> auteurs = auteurJPA.findAll();
        Optional<Auteur> res = auteurs.stream()
                .filter(a -> a.getNom().equals(nom))
                .findFirst();
        return res.orElse(null);
    }

    @Override
    public Auteur update(Auteur element) {
        assert element.getId() != null;
        return auteurJPA.save(element);
    }

    @Override
    public Auteur delete(Auteur element) {
        assert element.getId() != null;
        Optional<Auteur> search = auteurJPA.findById(element.getId());
        assert search.isPresent();

        Auteur deleteAuteur = search.get();
        Auteur copy = new Auteur(deleteAuteur.getId(), deleteAuteur.getNom());

        auteurJPA.deleteById(element.getId());
        return copy;
    }
}
