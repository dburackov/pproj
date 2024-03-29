package entities;

import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private int likeId;

    @Getter
    @Setter
    private int objectPetId;

    @Getter
    @Setter
    private int targetPetId;

    @Getter
    @Setter
    private Date date;
}
