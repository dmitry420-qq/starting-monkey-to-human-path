package learn.xml;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class TestXmlTask {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Owner owner = new Owner();
        String path = "C:\\Users\\User\\IdeaProjects\\starting-monkey-to-human-path\\PO71.Koloyarskiy.wdad\\learn\\xml\\notes.xml";
        owner.setName("ivan");
        owner.setMail("rrw@mail.ru");
        XmlTask xmlTask = new XmlTask();
        xmlTask.unmarhall(path);
        System.out.println(xmlTask.getNoteText(owner, "Заголово1к"));
        xmlTask.updateNote(owner, "Заголово1к", "Новый текст");
        System.out.println(xmlTask.getNoteText(owner, "Заголово1к"));
        xmlTask.marshall(path);
        User user = new User();
        user.setMail("erer@yandex.ru");
        user.setRights("RW");
        xmlTask.setPrivileges("Заголово1к", user, 1);
        xmlTask.marshall(path);
    }
}
