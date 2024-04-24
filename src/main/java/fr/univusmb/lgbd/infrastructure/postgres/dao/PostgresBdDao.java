package fr.univusmb.lgbd.infrastructure.postgres.dao;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostgresBdJPA;
import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresBdDao implements Dao<Bd>{

    @Autowired
    private PostgresBdJPA bdJPA;

    @Override
    public Bd save(Bd element) {
        assert element.getIsbn() != null;

        return bdJPA.save(element);
    }

    @Override
    public Optional<Bd> get(Long id) {
        return bdJPA.findById(id);
    }

    @Override
    public List<Bd> getAll() {
        return bdJPA.findAll();
    }

    @Override
    public Bd update(Bd element) {
        assert element.getIsbn() != null;
        return bdJPA.save(element);
    }

    @Override
    public Bd delete(Bd element) {
        assert element.getIsbn() != null;
        Optional<Bd> search = bdJPA.findById(element.getIsbn());
        assert search.isPresent();

        Bd deleteBd = search.get();
        Bd copy = new Bd(deleteBd.getIsbn(),deleteBd.getTitre(),deleteBd.getEditeur(),deleteBd.getAnnee());
        bdJPA.deleteById(element.getIsbn());
        return copy;
    }

}
