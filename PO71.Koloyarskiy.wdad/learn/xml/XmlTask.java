package learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class XmlTask implements XmlTaskes  {
    private Notes notes;
    private static Class DEFAULT_TYPE = Notes.class;

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public void unmarhall(String filePath) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(DEFAULT_TYPE);
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        setNotes((Notes) unmarshaller.unmarshal(new File(filePath)));
    }

    public void marshall(String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(DEFAULT_TYPE);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(notes, new FileOutputStream(filePath));
    }

    @Override
    public String getNoteText(Owner owner, String title) {
        for (int i = 0; i < notes.getNoteList().size(); i++) {
            if(notes.getNoteList().get(i).getOwnerList().contains(owner) && notes.getNoteList().get(i).getTitle().equals(title))
                return notes.getNoteList().get(i).getText();
        }
        return null;
    }

    @Override
    public void updateNote(Owner owner, String title, String newText) {
        for (int i = 0; i < notes.getNoteList().size(); i++) {
            if(notes.getNoteList().get(i).getOwnerList().contains(owner) && notes.getNoteList().get(i).getTitle().equals(title))
                notes.getNoteList().get(i).setText(newText);
        }
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) {
        List<User> userList;
        User newUser;
        for (int i = 0; i < notes.getNoteList().size(); i++) {
            userList = notes.getNoteList().get(i).getPrivileges().getUserList();
            if(!userList.contains(user) && notes.getNoteList().get(i).getTitle().equals(noteTitle)){
                userList.add(user);
                return;
            }
            if(userList.contains(user) && notes.getNoteList().get(i).getTitle().equals(noteTitle)) {
                newUser = userList.stream().filter(r -> r.equals(user)).findFirst().get();
                switch (newRights){
                    case 0:
                        userList.remove(newUser);
                        break;
                    case 1:
                        newUser.setRights("R");
                        break;
                    case 2:
                        newUser.setRights("RW");
                        break;
                }
            }
        }
    }
}
