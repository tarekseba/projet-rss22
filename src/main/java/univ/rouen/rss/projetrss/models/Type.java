package univ.rouen.rss.projetrss.models;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
public enum Type {
    @XmlEnumValue("GIF")
    GIF,
    @XmlEnumValue("JPEG")
    JPEG,
    @XmlEnumValue("JPG")
    JPG,
    @XmlEnumValue("BMP")
    BMP,
    @XmlEnumValue("PNG")
    PNG
}
