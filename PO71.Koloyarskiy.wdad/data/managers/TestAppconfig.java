package data.managers;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestAppconfig {
    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
        PreferenceManager preferenceManager = PreferenceManager.getInstance("C:\\Users\\User\\IdeaProjects\\starting-monkey-to-human-path\\PO71.Koloyarskiy.wdad\\resources\\configuration\\appconfig.xml");
        Appconfig appconfig = preferenceManager.getAppconfig();
        System.out.println(appconfig);
        preferenceManager.setProperty("appconfig.rmi.classprovider.server.registry.registryport", "1099");
        System.out.println(preferenceManager.getProperty("appconfig.rmi.classprovider.server"));
    }
}
