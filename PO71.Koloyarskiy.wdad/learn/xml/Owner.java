package learn.xml;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "owner")
public class Owner {
    @XmlAttribute(name = "mail", required = true)
    private String mail;
    @XmlAttribute(name = "name", required = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(mail, owner.mail) &&
                Objects.equals(name, owner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, name);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
