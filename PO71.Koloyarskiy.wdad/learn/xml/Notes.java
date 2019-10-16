package learn.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="notes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notes {
    @XmlElement(name="note")
    public List<Note> noteList;

    @Override
    public String toString() {
        return "notes{" +
                "noteList=" + noteList +
                '}';
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
