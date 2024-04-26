package fr.univusmb.lgbd.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {
    private String scrap;

    @PostMapping("/scrap")
    public ResponseEntity<Void> scrap(@RequestBody String body) {
        System.out.println("Scrap : " + body);
        this.scrap = body;
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scrap")
    public String getScrap() {
        return this.scrap;
    }
}