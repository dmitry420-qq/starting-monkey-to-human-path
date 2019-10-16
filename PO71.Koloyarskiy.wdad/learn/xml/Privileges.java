package learn.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "privileges")
public class Privileges {
    private All all;
    @XmlElement(name = "user")
    private List<User> userList;

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Privileges{" +
                "all='" + all + '\'' +
                ", userList=" + userList +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
