package fr.univusmb.lgbd.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScrapController {
    @PostMapping("/scrap")
    @ResponseBody
    public String scrap(@RequestBody String body) {
        return body;
    }
}