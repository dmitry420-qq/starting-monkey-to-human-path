package learn.rmi;

import learn.xml.Notes;
import learn.xml.Owner;
import learn.xml.User;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface XmlDataManager extends Remote {
    public void unmarhall(String filePath) throws JAXBException, RemoteException;
    public void marshall(String filePath) throws JAXBException, FileNotFoundException, RemoteException;
    public String getNote(Owner owner, String title) throws RemoteException;
    public void updateNote(Owner owner, String title, StringBuilder newText) throws RemoteException;
    public void setPrivileges (String noteTitle, User user, int newRights) throws RemoteException;
    public List<Notes> getNotes (User owner) throws RemoteException;
}
