package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

@XmlRootElement(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @XmlElement
    private String alt;
    @XmlElement
    private URI href;
    @XmlElement
    private int lenght;
    @XmlElement
    private Type type;
}
