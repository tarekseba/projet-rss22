package univ.rouen.rss.projetrss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univ.rouen.rss.projetrss.services.RPCService;

@RestController
public class XmlController {
    @Autowired
    private RPCService service;
    protected static String resourceName = "tp.xml";

    @RequestMapping(value = "/rss22/resume/xml",method = RequestMethod.GET,produces = "application/xml")
    public String getFlux() throws Exception {
        String xQuery = "declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"<items>{for $x in doc(\"" + resourceName + "\")//rss:feed/rss:item\n"
                +"return\n"
                +"<item>\n"
                +"{\n" +
                "if($x/rss:published/text())\n" +
                "then\n" +
                "<date>{format-date(xs:dateTime($x/rss:published/text()), \"[M01]/[D01]/[Y0001]\")}</date> \n" +
                "else\n" +
                "<date>{format-date(xs:dateTime($x/rss:updated/text()), \"[M01]/[D01]/[Y0001]\")}</date>\n" +
                "}\n"
                +"<title>{$x/rss:title/text()}</title><guid>{$x/rss:guid/text()}</guid></item>}\n"+
                "</items>";
        return service.get(xQuery);
    }

    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = "application/xml")
    public  String getFluxByGuid(@PathVariable("guid") String guid) throws Exception {
        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//rss:feed/rss:item where $x/rss:guid/text()='"+guid+"'\n"
                +"return $x";
        return service.get(xQuery);
    }

    @DeleteMapping(value = "/rss22/delete/{guid}",produces = "application/xml")
    public String deleteFluxByGuid(@PathVariable("guid") String guid) throws Exception {
        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//rss:feed/rss:item where $x/rss:guid/text()='"+guid+"'\n"
                +"return update delete $x";
        return service.get(xQuery);
    }

    @PostMapping(value = "/rss22/insert",consumes = "application/xml")
    public String addFlux(@RequestBody String flux) throws Exception {
        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\";\n"
                +"update insert "+flux+" into  collection('/db/rss22')/rss:feed";
        return service.get(xQuery);
    }
}
