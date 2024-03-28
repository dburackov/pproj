package databaserepos;

import java.util.Date;

public class Like {

    public Like() {

    }

    public Like(int object_pet_id, int target_pet_id, Date date) {
        this.object_pet_id = object_pet_id;
        this.target_pet_id = target_pet_id;
        this.date = date;
    }

    public int like_id = 0;
    public int object_pet_id = 0;
    public int target_pet_id = 0;
    public Date date = null;

    public static String sequence = "likes_like_id_seq";
}
