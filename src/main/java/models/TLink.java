package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TLink {
    @XmlAttribute(name = "href")
    private URI href;
    @XmlAttribute(name = "rel")
    private String rel;
    @XmlAttribute(name = "type")
    private String type;

    @Override
    public String toString() {
        return "TLink{" +
                "href=" + href +
                ", rel='" + rel + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
