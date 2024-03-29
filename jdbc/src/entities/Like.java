package entities;

import lombok.Data;

import java.util.Date;


@Data
public class Like {

    public Like() {
//
    }

    public Like(int objectPetId, int targetPetId, Date date) {
        this.objectPetId = objectPetId;
        this.targetPetId = targetPetId;
        this.date = date;
    }

    private int likeId;
    private int objectPetId;
    private int targetPetId;
    private Date date;
}
