package learn.rmi;

import jdk.internal.org.xml.sax.SAXException;
import learn.xml.Notes;
import learn.xml.Owner;
import learn.xml.User;
import learn.xml.XmlTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {
    private XmlTask xmlTask;
    private Notes notes;

    private static Class DEFAULT_TYPE = Notes.class;

    public void unmarhall(String filePath) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(DEFAULT_TYPE);
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        xmlTask.setNotes((Notes) unmarshaller.unmarshal(new File(filePath)));
    }

    public void marshall(String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(DEFAULT_TYPE);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(notes, new FileOutputStream(filePath));
    }

    public XmlDataManagerImpl(){
            xmlTask = new XmlTask();
    }

    @Override
    public String getNote(Owner owner, String title) {
       return xmlTask.getNoteText(owner, title);
    }

    @Override
    public void updateNote(Owner owner, String title, StringBuilder newText) {
        xmlTask.updateNote(owner, title, newText.toString());
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) {
        xmlTask.setPrivileges(noteTitle, user, newRights);
    }

    @Override
    public List<Notes> getNotes(User owner) {
        return (List<Notes>) xmlTask.getNotes();
    }
}
