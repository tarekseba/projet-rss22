package univ.rouen.rss.projetrss.services;

import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import java.lang.reflect.InvocationTargetException;

@Service
public class RPCService {
    protected static String DRIVER = "org.exist.xmldb.DatabaseImpl";
    protected static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    protected static String collectionPath = "/db/rss22";

    public String get(String xQuery) throws Exception {
        //Initialize data base driver
        Class cl=Class.forName(DRIVER);
        Database database=(Database)cl.getDeclaredConstructor().newInstance();
        DatabaseManager.registerDatabase(database);

        //get collection
        Collection col=DatabaseManager.getCollection(URI+collectionPath);

        //Query a document

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
        System.out.println(buffer.toString());
        return buffer.toString();
    }
}
