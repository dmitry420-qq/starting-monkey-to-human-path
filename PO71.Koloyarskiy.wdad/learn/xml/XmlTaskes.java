package learn.xml;

public interface XmlTaskes {
    public String getNoteText (Owner owner, String title);
    public void updateNote (Owner owner, String title, String newText);
    public void setPrivileges (String noteTitle, User user, int newRights);
}
