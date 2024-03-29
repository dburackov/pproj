package entities;

import lombok.Getter;
import lombok.Setter;

public class User {
    public User() {
        this.userId = 0;
        this.name = null;
        this.email = null;
        this.password = null;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;
}
