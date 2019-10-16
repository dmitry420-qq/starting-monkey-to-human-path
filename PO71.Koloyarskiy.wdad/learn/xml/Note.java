package learn.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="note")
public class Note {
    @XmlElement(name="title")
    private String title;
    @XmlElement(name = "text")
    private String text;
    @XmlElement(name="owner")
    private List<Owner> ownerList;
    @XmlElement(name = "privileges")
    private Privileges privileges;
    @XmlElement(name = "cdate")
    private String cdate;

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title) &&
                Objects.equals(text, note.text) &&
                Objects.equals(ownerList, note.ownerList) &&
                Objects.equals(privileges, note.privileges) &&
                Objects.equals(cdate, note.cdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, ownerList, privileges, cdate);
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", ownerList=" + ownerList +
                ", privileges=" + privileges +
                ", cdate='" + cdate + '\'' +
                '}';
    }

    public Privileges getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Privileges privileges) {
        this.privileges = privileges;
    }
}
