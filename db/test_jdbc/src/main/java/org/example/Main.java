package org.example;

//import org.postgresql.jdbc.AutoSave;

import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBController db = new DBController();

        User curr = new User("asdffds", "adsf", "qwerq");

//        curr.user_id = db.create_user(curr);
//        System.out.println(curr.user_id);

//        db.update_user(16, curr);

//        db.read_users();

//        curr = db.get_user(16);
//        System.out.println(curr.user_id + ' ' +  curr.name + ' ' + curr.email);

        db.delete_user(16);
        db.read_users();
    }
}