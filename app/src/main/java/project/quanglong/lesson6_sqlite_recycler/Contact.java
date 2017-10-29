package project.quanglong.lesson6_sqlite_recycler;

/**
 * Created by long on 10/27/2017.
 */

public class Contact {
    private int id;
    private String name;
    private String phonenumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Contact() {
    }

    public Contact(int id, String name, String phonenumber) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
    }
}
