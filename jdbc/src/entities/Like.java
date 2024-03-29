package entities;

import java.util.Date;

public class Like {

    public Like() {
//
    }

    public Like(int objectPetId, int targetPetId, Date date) {
        this.objectPetId = objectPetId;
        this.targetPetId = targetPetId;
        this.date = date;
    }

    public int likeId;
    public int objectPetId;
    public int targetPetId;
    public Date date;
}
