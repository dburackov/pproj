package entities;

public class User {
    public User() {
        this.userId = 0;
        this.name = null;
        this.email = null;
        this.password = null;
    };

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int userId;
    public String name;
    public String email;
    public String password;
}
