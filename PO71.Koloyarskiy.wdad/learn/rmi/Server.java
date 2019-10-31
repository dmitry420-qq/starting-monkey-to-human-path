package learn.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import data.managers.PreferenceManager;
import org.xml.sax.SAXException;
import utils.PreferencesManagerConstant;


public class Server {

    private static PreferenceManager preferencesManager;

    private static final String XML_DATA_MANAGER = "XmlDataManager";
    private static final int XML_DATA_MANAGER_PORT = 33333;

    private static final String filePath = "C:\\Users\\User\\IdeaProjects\\starting-monkey-to-human-path\\PO71.Koloyarskiy.wdad\\resources\\configuration\\appconfig.xml";

    public static void main(String[] args) throws IOException{

        try {
            preferencesManager = PreferenceManager.getInstance(filePath);
        }catch (IOException | SAXException | ParserConfigurationException | JAXBException ex) {
            ex.printStackTrace();
        }

        System.setProperty("java.rmi.server.codebase", PreferencesManagerConstant.CLASS_PROVIDER);
        System.setProperty("java.rmi.server.UseCodeBaseOnly", preferencesManager.getProperty(PreferencesManagerConstant.USE_CODE_BASE_ONLY));
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("java.security.policy", preferencesManager.getProperty(PreferencesManagerConstant.POLICY_PATH));
        System.setSecurityManager(new SecurityManager());

        Registry registry = null;
        try{

            if(preferencesManager.getProperty(PreferencesManagerConstant.CREATE_REGISTRY).equals("yes"))
                registry = LocateRegistry.createRegistry(Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstant.REGISTRY_PORT)));
            else
                registry = LocateRegistry.getRegistry(Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstant.REGISTRY_PORT)));

        }catch (RemoteException ex){
            ex.printStackTrace();
            System.err.println("cant locate registry");
        }

        if(registry != null){
            try{
                System.out.println("exporting object ...");
                XmlDataManagerImpl xmlDataManagerImpl = new XmlDataManagerImpl();
                UnicastRemoteObject.exportObject(xmlDataManagerImpl, XML_DATA_MANAGER_PORT);//убрать каст Remote
                registry.rebind(XML_DATA_MANAGER, xmlDataManagerImpl);//убрать каст Remote
                preferencesManager.addBindedObject(XML_DATA_MANAGER, "PO71.Koloyarskiy.wdad.learn.rmi.XmlDataMangerImpl");
                System.out.println("Waiting ... ");
                System.out.println("Input \"exit\" to close server.");
                Scanner scanner = new Scanner(System.in);
                while(true){
                    String input = scanner.nextLine();
                    if(input.equals("exit")) {
                        try {
                            registry.unbind(XML_DATA_MANAGER);
                            preferencesManager.removeBindedObject(XML_DATA_MANAGER);
                            System.exit(0);
                        }catch (NotBoundException ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }catch (RemoteException ex){
                ex.printStackTrace();
                System.err.println("cant export or bind object");
            }
        }
    }
}
