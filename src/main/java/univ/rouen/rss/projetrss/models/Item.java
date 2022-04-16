package univ.rouen.rss.projetrss.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @XmlAttribute
    private String guid;
    @XmlElement
    private String title;
    @XmlElement
    private Category category;
    @XmlElement
    private Date published;
    @XmlElement
    private Image image;
    @XmlElement
    private String content;
    @XmlElement
    private Author author;
}
