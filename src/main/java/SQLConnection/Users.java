package SQLConnection;

public class Users {

    private int ID, Age;
    private String FullName;

    public Users() {
    }

    public Users(int ID, int age, String fullName) {
        this.ID = ID;
        Age = age;
        FullName = fullName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
