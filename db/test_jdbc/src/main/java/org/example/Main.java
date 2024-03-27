package org.example;

//import org.postgresql.jdbc.AutoSave;

import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserRepository db = new UserRepository();

        User curr = new User("yyyyyyyy", "ttt", "zxccvv");

//        curr.user_id = db.create_user(curr);
//        System.out.println(curr.user_id);

//        curr.name = "RRRRRRR";
//        db.update_user(17, curr);
//
//        curr = db.get_user(17);
//        System.out.println(curr.user_id + ' ' +  curr.name + ' ' + curr.email);
//
//        db.delete_user(17);
//        db.read_users();

//        PetProfileRepository ppr = new PetProfileRepository();
//        PetProfile pp = new PetProfile(1, Purpose.walking);

//        pp.pet_profile_id = ppr.create(pp);

//        pp.purpose = Purpose.procreation;
//        ppr.update(pp.pet_profile_id, pp);

//        pp = ppr.get(6);
//        System.out.println(pp.purpose);

//        ppr.delete(pp.pet_profile_id);

//        ppr.read_all();


        Date d = new Date(92, 2, 2);
        System.out.println(d.toString());

        String output = String.format("boolean variable is %b",true);
        System.out.print(output);


        PassportRepository passr = new PassportRepository();
        Passport passport = new Passport(3, "MJ", new Date(92, 3, 6),
                Kind.cat, "homeless", false, "red", "beautiful");

        passport.name = "TTTT";
        System.out.println(passr.update(3, passport));



    }
}