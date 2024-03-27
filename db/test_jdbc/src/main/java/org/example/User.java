package org.example;

public class User {
    public User() {
        this.user_id = 0;
        this.name = null;
        this.email = null;
        this.password = null;
    };

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(int user_id, String name, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int user_id;
    public String name;
    public String email;
    public String password;

    public static String sequence = "users_user_id_seq";
}
