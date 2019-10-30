package data.managers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class PreferenceManager {
    private Appconfig appconfig;
    private String filePath;
    private static PreferenceManager instance;
    private Document doc;

    private final static Class DEFAULT_TYPE = Appconfig.class;

    private PreferenceManager(String filePath) throws JAXBException, IOException, ParserConfigurationException, SAXException {
        this.filePath = filePath;
        this.appconfig = unmarshalObjectFromXML(filePath);
        generateDoc(filePath);
    }

    public static PreferenceManager getInstance(String filePath) throws JAXBException, IOException, ParserConfigurationException, SAXException {
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

    @Deprecated
    public void setClassprovider(String classProvider) {
        appconfig.getRmi().setClassProvider(classProvider);
    }

    @Deprecated
    public String getClassprovider() {
        return appconfig.getRmi().getClassProvider();
    }

    @Deprecated
    public List<Registry> getRegistries() {
        return appconfig.getRmi().getServer().getRegistries();
    }

    @Deprecated
    public void setRegistries(List<Registry> registries) {
        appconfig.getRmi().getServer().setRegistries(registries);
    }

    @Deprecated
    public List<BindedObject> getBindedObjects() {
        return appconfig.getRmi().getServer().getBindedObjects();
    }

    @Deprecated
    public void setBindedObjects(List<BindedObject> bindedObjects) {
        appconfig.getRmi().getServer().setBindedObjects(bindedObjects);
    }

    @Deprecated
    public void setPolicyPath(String policyPath) {
        appconfig.getRmi().getClient().setPolicyPath(policyPath);
    }

    public String getPolicyPath() {
        return appconfig.getRmi().getClient().getPolicyPath();
    }

    @Deprecated
    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        appconfig.getRmi().getClient().setUseCodeBaseOnly(useCodeBaseOnly);
    }
    @Deprecated
    public String getUseCodeBaseOnly() {
        return appconfig.getRmi().getClient().getUseCodeBaseOnly();
    }

    public void setProperty(String key, String value) throws  IOException {
        getPropertyElement(key).setTextContent(value);
        updateDoc();
    }

    public String getProperty(String key) {
        return getPropertyElement(key).getTextContent();
    }

    public void setProperties(Properties prop) throws IOException {
        for (String key : prop.stringPropertyNames()) {
            setProperty(key, prop.getProperty(key));
        }
    }

    public Properties getProperties() {
        Properties props = new Properties();
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//*[not(*)]";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                String key = getNodePath(nodeList.item(i));
                props.put(key, getProperty(key));
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    public void addBindedObject(String name, String className) throws IOException {
        Element bindedObject = doc.createElement("bindedobject");
        bindedObject.setAttribute("name", name);
        bindedObject.setAttribute("class", className);
        Element server = (Element)doc.getElementsByTagName("server").item(0);
        server.appendChild(bindedObject);
        updateDoc();
    }

    public void removeBindedObject(String name) throws IOException {
        NodeList bindedObjects = doc.getElementsByTagName("bindedobject");
        for (int i = 0; i < bindedObjects.getLength(); i++) {
            Element bindedObject = (Element)bindedObjects.item(i);
            if (name.equals(bindedObject.getAttribute("name"))) {
                bindedObject.getParentNode().removeChild(bindedObject);
            }
        }
        updateDoc();
    }

    private String getNodePath(Node node) {
        Node parent = node.getParentNode();
        if (parent.getNodeName().equals("#document")) {
            return node.getNodeName();
        }
        return getNodePath(parent) + '.' + node.getNodeName();
    }

    private void generateDoc(String path) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(xmlFile);
    }

    private void updateDoc() throws IOException {
        DOMImplementationLS domImplementationLS =
                (DOMImplementationLS) doc.getImplementation().getFeature("LS", "3.0");
        LSOutput lsOutput = domImplementationLS.createLSOutput();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        lsOutput.setByteStream(outputStream);
        LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
        lsSerializer.write(doc, lsOutput);
        outputStream.close();
    }

    private Element getPropertyElement(String key){
        String[] tags = key.split("\\.");
        return (Element)doc.getElementsByTagName(tags[tags.length - 1]).item(0);
    }
}
