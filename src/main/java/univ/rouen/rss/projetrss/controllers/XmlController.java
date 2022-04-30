package univ.rouen.rss.projetrss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.rouen.rss.projetrss.services.RPCService;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
@RestController
public class XmlController {
    @Autowired
    private RPCService service;
    protected static String resourceName = "tp2.xml";

    private static File file=new File("src/main/resources/xsd/rss22.tp1.xsd");

    @RequestMapping(value = "/rss22/resume/xml",method = RequestMethod.GET,produces = "application/xml")
    public String getFluxXML() throws Exception {
        String xQuery = "declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"<items>{for $x in doc(\"" + resourceName + "\")//feed/item\n"
                +"return\n"
                +"<item>\n"
                +"{\n" +
                "if($x/published/text())\n" +
                "then\n" +
                "<date>{format-date(xs:dateTime($x/published/text()), \"[M01]/[D01]/[Y0001]\")}</date> \n" +
                "else\n" +
                "<date>{format-date(xs:dateTime($x/updated/text()), \"[M01]/[D01]/[Y0001]\")}</date>\n" +
                "}\n"
                +"<title>{$x/title/text()}</title><guid>{$x/guid/text()}</guid></item>}\n"+
                "</items>";
        return service.xQueryRequest(xQuery);
    }

    @RequestMapping(value = "/rss22/resume/html",method = RequestMethod.GET,produces = "text/html")
    public String getFluxHtml() throws Exception{

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in collection('/db/rss22/')//feed\n"
                +"return $x";

        String xml= service.xQueryRequest(xQuery);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer(new StreamSource("src/main/resources/templates/item.xslt"));
        StringWriter writer=new StringWriter();
        transformer.transform(new StreamSource(new StringReader(xml.replaceAll("rss:",""))),new StreamResult(writer));

        return writer.toString();
    }

    @GetMapping(value = "/rss22/resume/html/{guid}")
    public  String getFluxByGuidHTML(@PathVariable("guid") String guid, Model model) throws Exception {

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//feed/item where $x/guid/text()='"+guid+"'\n"
                +"return $x";

        String xml= service.xQueryRequest(xQuery);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer(new StreamSource("src/main/resources/templates/itembyguid.xslt"));
        StringWriter writer=new StringWriter();
        transformer.transform(new StreamSource(new StringReader(xml.replaceAll("rss:",""))),new StreamResult(writer));

        return writer.toString();
    }

    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = "application/xml")
    public  String getFluxByGuidXML(@PathVariable("guid") String guid) throws Exception {

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//feed/item where $x/guid/text()='"+guid+"'\n"
                +"return $x";

        return service.xQueryRequest(xQuery);
    }

    @DeleteMapping(value = "/rss22/delete/{guid}",produces = "application/xml")
    public String deleteFluxByGuid(@PathVariable("guid") String guid) throws Exception {

        System.out.println(guid);

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\"\n;"
                +"for $x in doc(\"" + resourceName + "\")//feed/item where $x/guid/text()='"+guid+"'\n"
                +"return update delete $x";

        return service.xQueryRequest(xQuery);
    }

    @PostMapping(value = "/rss22/insert")
    public ResponseEntity addFlux(@RequestBody String flux) throws Exception {

        String validXml=flux.replaceAll("rss:","");

        String xQuery="declare namespace rss=\"http://univrouen.fr/rss22\";\n"
                +"update insert "+validXml+" into  collection('/db/rss22')/feed";

        System.out.println(validXml);

        if(service.validXMLFlux(file,validXml)){
            service.xQueryRequest(xQuery);
            System.out.println("HAHAHAHAHAHA");
            return new ResponseEntity("status:succes", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("status:error",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

}
