package learn.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ALL")
@XmlAccessorType(XmlAccessType.FIELD)
public class All {
    @Override
    public String toString() {
        return "All{" +
                "rights='" + rights + '\'' +
                '}';
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    @XmlAttribute(name = "rights")
    private String rights;
}
