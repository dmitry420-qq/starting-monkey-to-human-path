package data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bindedobject")
public class BindedObject {
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "class", required = true)
    private String type;

    public BindedObject(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public BindedObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BindedObject.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
