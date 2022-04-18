package models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item implements Serializable {
    @XmlElement(name = "guid")
    private String guid;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "category")
    private Category category;
    @XmlElement(name = "published")
    private Date published;
    @XmlElement(name = "image")
    private Image image;
    @XmlElement(name = "content")
    private String content;
    @XmlElement(name = "author")
    private Author author;


    @Override
    public String toString() {
        return "Item{" +
                "guid='" + guid + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", published=" + published +
                ", image=" + image.toString() +
                ", content='" + content + '\'' +
                ", author=" + author.toString() +
                '}';
    }
}
