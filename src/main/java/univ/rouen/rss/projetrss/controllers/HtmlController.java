package univ.rouen.rss.projetrss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/")
    public  String getHome(){
        return "index.html";
    }

    @GetMapping("/help")
    public  String getHelp(){
        return "help.html";
    }

    @GetMapping("/resume/html")
    public  String getHtmlFlux(){
        return "htmlFlux.html";
    }
}
