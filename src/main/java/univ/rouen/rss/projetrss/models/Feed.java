package univ.rouen.rss.projetrss.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "feed")
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @XmlAttribute
    private String lang;
    private List<Item> items;
}
