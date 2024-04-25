package fr.univusmb.lgbd.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = { "*" })
public class ScrapController {
    
    @PostMapping("/scrap")
    public ResponseEntity<Void> scrap(@RequestParam("url") String url) {
        System.out.println("SCRAP : url : " + url);
        return ResponseEntity.ok().build();
    }
}