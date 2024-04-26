package fr.univusmb.lgbd.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresBdDao;
import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresSerieDao;
import fr.univusmb.lgbd.model.Serie;

@RestController
@RequestMapping("/serie")
@CrossOrigin(origins = { "*" })
public class SerieController {
    
    @Autowired
    private PostgresSerieDao serieDao;
    private PostgresBdDao bdDao;

    @GetMapping("/all")
    public ResponseEntity<List<Serie>> get() {
        return ResponseEntity.ok(serieDao.getAll());
    }

    @GetMapping("/serieCount")
    public ResponseEntity<Long> count() {
        long count = serieDao.getAll().size();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/get")
    public ResponseEntity<Serie> get(@RequestParam("name") String name) {
        Serie find = serieDao.getByName(name);
        return ResponseEntity.ok(find);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestParam("name") String name) {
        System.out.println("CREATE : name : " + name);
        assert name != null;
        Serie newSerie = new Serie(null, name);
        serieDao.save(newSerie);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestParam("id") Long id, @RequestParam("name") String name) {
        System.out.println("UPDATE : id : " + id + " name : " + name);
        assert id != null;
        assert name != null;
        Serie serie = serieDao.get(id).get();
        serie.setNom(name);
        serieDao.update(serie);
        return ResponseEntity.ok().build();
    }



}
