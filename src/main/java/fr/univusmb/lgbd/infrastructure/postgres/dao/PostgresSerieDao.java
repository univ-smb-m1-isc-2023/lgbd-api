package fr.univusmb.lgbd.infrastructure.postgres.dao;

import fr.univusmb.lgbd.model.Bd;
import fr.univusmb.lgbd.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostgresSerieJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class PostgresSerieDao implements Dao<Serie>{
    @Autowired
    private PostgresSerieJPA serieJPA;

    private Random random = new Random();

    private Long generateUniqueId(){
        Long id = random.nextLong();
        if(serieJPA.findById(id).isPresent()){
            return generateUniqueId();
        }
        return id;
    }

    @Override
    public Serie save(Serie element) {
        assert element.getId() == null;
        Long uniqueId = generateUniqueId();
        Serie newSerie = new Serie(uniqueId, element.getNom());
        return serieJPA.save(newSerie);
    }

    @Override
    public Optional<Serie> get(Long id) {
        return serieJPA.findById(id);
    }

    @Override
    public List<Serie> getAll() {
        return serieJPA.findAll();
    }

    @Override
    public Serie update(Serie element) {
        assert element.getId() != null;
        return serieJPA.save(element);
    }

    public Serie getByName(String nom){
        List<Serie> series = serieJPA.findAll();
        Optional<Serie> res = series.stream().filter(
                a-> a.getNom().equals(nom)
        ).findFirst();
        return res.orElse(null);
    }

    @Override
    public Serie delete(Serie element) {
        assert element.getId() != null;
        Optional<Serie> search = serieJPA.findById(element.getId());
        assert search.isPresent();
    
        Serie deleteSerie = search.get();
        Serie copy = new Serie(deleteSerie.getId(),deleteSerie.getNom());
        serieJPA.deleteById(element.getId());
        return copy;
    }
}
