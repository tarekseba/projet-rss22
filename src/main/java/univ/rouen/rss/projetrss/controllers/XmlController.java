package univ.rouen.rss.projetrss.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.*;
import univ.rouen.rss.projetrss.models.Feed;
import univ.rouen.rss.projetrss.models.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class XmlController {

    @RequestMapping(value = "/rss22/resume/xml",method = RequestMethod.GET,produces = "application/xml")
    public String getFlux(){

        StringBuffer buffer=new StringBuffer();

        try {
            URL url = new URL("http://10.130.163.32:8080/exist/rest/db/rss22");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setRequestProperty("Content-Type", "application/xml");

            String request="<query xmlns=\"http://exist.sourceforge.net/NS/exist\"\n" +
                    "xmlns:rss=\"http://univrouen.fr/rss22\"" +
                    ">\n" +
                    "  <text>\n" +
                    "    for $x in //rss:feed/rss:item\n" +
                    "    return ($x/rss:title, $x/rss:guid, $x/rss:published)\n" +
                    "  </text>\n" +
                    "</query>";

            OutputStream os = connection.getOutputStream();
            os.write(request.getBytes());
            os.flush();

            if (connection.getResponseCode() > 299) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String response;
            while ((response = br.readLine()) != null) {
                buffer.append(response);
            }

            connection.disconnect();

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = "application/xml")
    public  String getFluxByGuid(@PathVariable("guid") String guid){

        StringBuffer buffer=new StringBuffer();

        try {
            URL url = new URL("http://10.130.163.32:8080/exist/rest/db/rss22");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setRequestProperty("Content-Type", "application/xml");

            System.out.println(guid);

            String request="<query xmlns=\"http://exist.sourceforge.net/NS/exist\"\n" +
                    "xmlns:rss=\"http://univrouen.fr/rss22\"" +
                    ">\n" +
                    "  <text>\n" +
                    "    for $x in //rss:feed/rss:item where $x//rss:guid/text()='"+guid+"' \n" +
                    "    return ($x)\n" +
                    "  </text>\n" +
                    "</query>";

            OutputStream os = connection.getOutputStream();
            os.write(request.getBytes());
            os.flush();

            if (connection.getResponseCode() > 299) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String response;
            while ((response = br.readLine()) != null) {
                buffer.append(response);
            }

            connection.disconnect();

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return buffer.toString();

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
