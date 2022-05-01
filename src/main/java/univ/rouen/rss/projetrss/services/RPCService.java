package univ.rouen.rss.projetrss.services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

@Service
public class RPCService {
    protected static String DRIVER = "org.exist.xmldb.DatabaseImpl";
    //protected static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    protected static String URI = "xmldb:exist://exist-tarek-xml.cleverapps.io/exist/xmlrpc";
    protected static String collectionPath = "/db/rss22";

    public String xQueryRequest(String xQuery) throws Exception {
        //Initialize data base driver
        Class cl=Class.forName(DRIVER);
        Database database=(Database)cl.getDeclaredConstructor().newInstance();
        DatabaseManager.registerDatabase(database);

        //get collection
        Collection col=DatabaseManager.getCollection(URI+collectionPath);

        //Instanciation a query service
        XQueryService service=(XQueryService)col.getService("XQueryService","1.0");
        service.clearNamespaces();
        service.setProperty("indent","yes");
        //Execute query print result
        ResourceSet result=service.query(xQuery);
        ResourceIterator i=result.getIterator();

        StringBuffer buffer=new StringBuffer();

        while (i.hasMoreResources()){
            Resource r=i.nextResource();
            buffer.append((String)r.getContent());
        }
        return buffer.toString();
    }

    public boolean validXMLFlux(File file,String xmlString){
        Source xml=new StreamSource(new StringReader(xmlString));
        SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema=schemaFactory.newSchema(file);
            Validator validator=schema.newValidator();
            validator.validate(xml);
            System.out.println("IS VALID");
        } catch (IOException|SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public  void transformXSL(String xml,File html) throws Exception {
        // Création du reader initial
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        XMLReader reader = spf.newSAXParser().getXMLReader();

        // Creation du filtre à appliquer au reader
        SAXTransformerFactory stf = (SAXTransformerFactory) TransformerFactory.newInstance();
        XMLFilter filtre = stf.newXMLFilter(new StreamSource (new File("src/main/resources/xsd/item.xslt")));

        // On "lie" le reader au filtre
        filtre.setParent(reader);

        // Création de la source
        SAXSource source = new SAXSource(filtre, new InputSource(new StringReader(xml)));

        StreamResult resultat = new StreamResult(new FileOutputStream(html));

        // Transformation
        Transformer transformer = stf.newTransformer();
        transformer.transform(source, resultat);
    }

}
