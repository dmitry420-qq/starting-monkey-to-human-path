package data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rmi")
public class Rmi {
    @XmlElement(required = true)
    private Server server;
    @XmlElement(required = true)
    private Client client;
    @XmlElement(name="classprovider")
    private String classProvider;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getClassProvider() {
        return classProvider;
    }

    public void setClassProvider(String classProvider) {
        this.classProvider = classProvider;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Rmi.class.getSimpleName() + "[", "]")
                .add("server=" + server)
                .add("client=" + client)
                .add("classProvider='" + classProvider + "'")
                .toString();
    }
}