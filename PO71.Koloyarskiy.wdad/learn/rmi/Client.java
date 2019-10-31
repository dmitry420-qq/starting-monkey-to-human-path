package learn.rmi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import data.managers.PreferenceManager;
import learn.xml.Owner;
import learn.xml.XmlTask;
import org.xml.sax.SAXException;
import utils.PreferencesManagerConstant;

public class Client {

    private static PreferenceManager preferencesManager;
    private static final String XML_DATA_MANAGER = "XmlDataManager";

    private static final String filePath = "C:\\Users\\User\\IdeaProjects\\starting-monkey-to-human-path\\PO71.Koloyarskiy.wdad\\resources\\configuration\\appconfig.xml";

    public static void main(String[] args) throws NotBoundException{

        try {
            preferencesManager = PreferenceManager.getInstance(filePath);
        } catch (ParserConfigurationException | IOException | SAXException | JAXBException ex) {
            ex.printStackTrace();
        }

        System.setProperty("java.rmi.server.codebase", PreferencesManagerConstant.CLASS_PROVIDER);
        System.setProperty("java.rmi.server.useCodebaseOnly", preferencesManager.getProperty(PreferencesManagerConstant.USE_CODE_BASE_ONLY));
        System.setProperty("java.security.policy", preferencesManager.getProperty(PreferencesManagerConstant.POLICY_PATH));
        System.setSecurityManager(new SecurityManager());

        Registry registry = null;
        try{
            registry = LocateRegistry.getRegistry(
                    preferencesManager.getProperty(PreferencesManagerConstant.REGISTRY_ADDRESS),
                    Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstant.REGISTRY_PORT)));
        } catch (RemoteException ex) {
            ex.printStackTrace();
            System.err.println("cant locate registry");
        }
        if(registry != null){
            try{
                XmlDataManager xmlDataManager = (XmlDataManager) registry.lookup(XML_DATA_MANAGER);
                doWork(xmlDataManager);
            }catch (RemoteException | NotBoundException e){
                System.err.println("Your code don't work");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doWork(XmlDataManager xmlDataManager) throws RemoteException, JAXBException, FileNotFoundException {
        Owner owner = new Owner();
        owner.setName("ivan");
        owner.setMail("rrw@mail.ru");
        xmlDataManager.unmarhall(filePath);
        xmlDataManager.getNote(owner, "Заголово1к");
        xmlDataManager.marshall(filePath);
    }
}
