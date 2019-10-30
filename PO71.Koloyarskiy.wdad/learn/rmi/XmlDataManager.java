package learn.rmi;

import learn.xml.Notes;
import learn.xml.User;

import java.util.List;

public interface XmlDataManager {
    public String getNote(User owner, String title);
    public void updateNote(User owner, String title, StringBuilder newText);
    public void setPrivileges (String noteTitle, User user, int newRights);
    public List<Notes> getNotes (User owner);
}
