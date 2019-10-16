package data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "appconfig")
public class Appconfig {
    @XmlElement(required = true)
    private Rmi rmi;

    public Rmi getRmi() {
        return rmi;
    }

    public void setRmi(Rmi rmi) {
        this.rmi = rmi;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Appconfig.class.getSimpleName() + "[", "]")
                .add("rmi=" + rmi)
                .toString();
    }
}