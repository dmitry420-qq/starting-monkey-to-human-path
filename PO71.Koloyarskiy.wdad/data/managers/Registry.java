package data.managers;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "registry")
public class Registry {
    @XmlElement(name="createregistry",required = true)
    private String createRegistry;
    @XmlElement(name="registryaddress",required = true)
    private String registryAddress;
    @XmlElement(name="registryport",required = true)
    private int registryPort;

    public Registry(String createRegistry, String registryAddress, int registryPort) {
        this.createRegistry = createRegistry;
        this.registryAddress = registryAddress;
        this.registryPort = registryPort;
    }

    public Registry() {
    }

    public String getCreateRegistry() {
        return createRegistry;
    }

    public void setCreateRegistry(String createRegistry) {
        this.createRegistry = createRegistry;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public int getRegistryPort() {
        return registryPort;
    }

    public void setRegistryPort(int registryPort) {
        this.registryPort = registryPort;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Registry.class.getSimpleName() + "[", "]")
                .add("createRegistry='" + createRegistry + "'")
                .add("registryAddress='" + registryAddress + "'")
                .add("registryPort=" + registryPort)
                .toString();
    }
}
