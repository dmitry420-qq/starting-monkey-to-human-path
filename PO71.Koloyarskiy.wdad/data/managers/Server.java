package data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "server")
public class Server {
    @XmlElement(name="registry", required = true)
    private List<Registry> registries;
    @XmlElement(name="bindedobject")
    private List<BindedObject> bindedObjects;

    public List<Registry> getRegistries() {
        return registries;
    }

    public void setRegistries(List<Registry> registries) {
        this.registries = registries;
    }

    public List<BindedObject> getBindedObjects() {
        return bindedObjects;
    }

    public void setBindedObjects(List<BindedObject> bindedObjects) {
        this.bindedObjects = bindedObjects;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Server.class.getSimpleName() + "[", "]")
                .add("registries=" + registries)
                .add("bindedObjects=" + bindedObjects)
                .toString();
    }
}
