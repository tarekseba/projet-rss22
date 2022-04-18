package univ.rouen.rss.projetrss.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Feed implements Serializable {
    @XmlAttribute(name = "lang")
    private String lang;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "pubDate")
    private Date pubDate;
    @XmlElement(name = "copyright")
    private String copyright;
    @XmlElement(name = "link")
    private TLink href;
    @XmlElementWrapper(name = "item")
    @XmlElements({
            @XmlElement(name = "guid",type = String.class),
            @XmlElement(name = "title",type = String.class),
            @XmlElement(name = "category",type = Category.class),
            @XmlElement(name = "updated",type = Date.class),
            @XmlElement(name = "image",type = Image.class),
            @XmlElement(name = "content",type = String.class),
            @XmlElement(name = "author",type = Author.class)
    })
    private List<Item> item=new ArrayList<Item>();


    public List<Item> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "lang='" + lang + '\'' +
                ", title='" + title + '\'' +
                ", pubDate=" + pubDate +
                ", copyright='" + copyright + '\'' +
                ", href='" + href.toString() + '\'' +
                ", items=" + item.toString() +
                '}';
    }
}
