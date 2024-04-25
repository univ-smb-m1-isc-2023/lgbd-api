package fr.univusmb.lgbd.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {

    @PostMapping("/scrap")
    public ResponseEntity<Void> scrap(@RequestBody String body) {
        System.out.println("Scrap : " + body);
        return ResponseEntity.ok().build();
    }
}