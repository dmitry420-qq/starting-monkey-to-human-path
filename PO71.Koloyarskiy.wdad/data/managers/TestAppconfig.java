package data.managers;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class TestAppconfig {
    public static void main(String[] args) throws JAXBException, IOException {
        PreferenceManager preferenceManager = PreferenceManager.getInstance("C:\\Users\\User\\IdeaProjects\\starting-monkey-to-human-path\\PO71.Koloyarskiy.wdad\\resources\\configuration\\appconfig.xml");
        Appconfig appconfig = preferenceManager.getAppconfig();
        System.out.println(appconfig);
    }
}
