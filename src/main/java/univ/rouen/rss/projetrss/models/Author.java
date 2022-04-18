package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Author implements Serializable {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "email")
    private String mail;
    @XmlElement(name = "uri")
    private String uri;

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
