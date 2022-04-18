package univ.rouen.rss.projetrss.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "image")
@XmlAccessorType(XmlAccessType.PROPERTY)
@ToString
public class Image implements Serializable {
    @XmlAttribute(name = "alt")
    private String alt;
    @XmlAttribute(name = "href")
    private String href;
    @XmlAttribute(name = "length")
    private int lenght;
    @XmlAttribute(name = "type")
    String type;

}
