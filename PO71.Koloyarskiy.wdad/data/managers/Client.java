package data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="client")
public class Client {
    @XmlElement(name="policypath")
    private String policyPath;
    @XmlElement(name="usecodebaseonly")
    private String useCodeBaseOnly;

    public Client(String policyPath, String useCodeBaseOnly) {
        this.policyPath = policyPath;
        this.useCodeBaseOnly = useCodeBaseOnly;
    }

    public Client() {
    }

    public String getPolicyPath() {
        return policyPath;
    }

    public void setPolicyPath(String policyPath) {
        this.policyPath = policyPath;
    }

    public String getUseCodeBaseOnly() {
        return useCodeBaseOnly;
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        this.useCodeBaseOnly = useCodeBaseOnly;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("policyPath='" + policyPath + "'")
                .add("useCodeBaseOnly='" + useCodeBaseOnly + "'")
                .toString();
    }
}
