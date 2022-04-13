package univ.rouen.rss.projetrss.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlController {
    @GetMapping("/rss22/resume/xml")
    public  String getFlux(){
        return "";
    }

    @GetMapping("/rss22/resume/xml/{guid}")
    public  String getFluxByGuid(){
        return "";
    }

    @PostMapping("/rss22/insert")
    public String postFlux(){
        return "Flux ajouté avec succés";
    }
    @DeleteMapping("/rss22/delete/{guid}")
    public String deleteFluxByGuid(){
        return "Flux supprimé avec succés";
    }
}
