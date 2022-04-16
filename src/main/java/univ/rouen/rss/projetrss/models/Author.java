package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
@XmlRootElement(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @XmlElement
    private String name;
    @XmlElement
    private String mail;
    @XmlElement
    private URI uri;
}
