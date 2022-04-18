package univ.rouen.rss.projetrss.controllers;

import org.apache.tools.ant.filters.StringInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import univ.rouen.rss.projetrss.services.RPCService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

@Controller
public class HtmlController {

    @Autowired
    private RPCService service;
    protected static String resourceName = "tp.xml";

    @GetMapping("/")
    public  String getHome(){
        return "index.html";
    }

    @GetMapping("/help")
    public  String getHelp(){
        return "help.html";
    }

    @GetMapping("/html")
    public  String getHtmlFlux() throws Exception {

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//rss:feed\n"
                +"return $x";

        String xml=service.get(xQuery);

        service.transformXSL(xml,new File("src/main/resources/static/flux.html"));

        return "flux.html";
    }
}
