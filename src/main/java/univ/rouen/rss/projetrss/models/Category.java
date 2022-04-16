package univ.rouen.rss.projetrss.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @XmlAttribute(name = "term")
    private String term;
}
