package data.managers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PreferenceManager {
    private Appconfig appconfig;
    private String filePath;
    private static PreferenceManager instance;

    private final static Class DEFAULT_TYPE = Appconfig.class;

    private PreferenceManager(String filePath) throws JAXBException, IOException {
        this.filePath = filePath;
        this.appconfig = unmarshalObjectFromXML(filePath);
    }

    public static PreferenceManager getInstance(String filePath) throws JAXBException, IOException {
        if (instance == null) {
            instance = new PreferenceManager(filePath);
        }
        return instance;
    }

    private Appconfig unmarshalObjectFromXML(String filePath) throws JAXBException, IOException {
        StringReader stringReader = new StringReader(new String(Files.readAllBytes(Paths.get(filePath))));
        JAXBContext context = JAXBContext.newInstance(DEFAULT_TYPE);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Appconfig) unmarshaller.unmarshal(stringReader);
    }

    public void marshalObjectToXML(String filePath) throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(DEFAULT_TYPE);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(appconfig, new FileOutputStream(filePath));
    }

    public Appconfig getAppconfig() {
        return appconfig;
    }

    public Rmi getRmi() {
        return appconfig.getRmi();
    }

    public void setRmi(Rmi rmi) {
        appconfig.setRmi(rmi);
    }

    public Server getServer() {
        return appconfig.getRmi().getServer();
    }

    public void setServer(Server server) {
        appconfig.getRmi().setServer(server);
    }

    public Client getClient() {
        return appconfig.getRmi().getClient();
    }

    public void setClient(Client client) {
        appconfig.getRmi().setClient(client);
    }

    public void setClassprovider(String classProvider) {
        appconfig.getRmi().setClassProvider(classProvider);
    }

    public String getClassprovider() {
        return appconfig.getRmi().getClassProvider();
    }

    public List<Registry> getRegistries() {
        return appconfig.getRmi().getServer().getRegistries();
    }

    public void setRegistries(List<Registry> registries) {
        appconfig.getRmi().getServer().setRegistries(registries);
    }

    public List<BindedObject> getBindedObjects() {
        return appconfig.getRmi().getServer().getBindedObjects();
    }

    public void setBindedObjects(List<BindedObject> bindedObjects) {
        appconfig.getRmi().getServer().setBindedObjects(bindedObjects);
    }

    public void setPolicyPath(String policyPath) {
        appconfig.getRmi().getClient().setPolicyPath(policyPath);
    }

    public String getPolicyPath() {
        return appconfig.getRmi().getClient().getPolicyPath();
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        appconfig.getRmi().getClient().setUseCodeBaseOnly(useCodeBaseOnly);
    }

    public String getUseCodeBaseOnly() {
        return appconfig.getRmi().getClient().getUseCodeBaseOnly();
    }
}
