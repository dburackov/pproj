package entities;


import lombok.Getter;
import lombok.Setter;

public class Match {
    public Match() {

    }

    public Match(int firstPetId, int secondPetId) {
        this.firstPetId = firstPetId;
        this.secondPetId = secondPetId;
    }

    @Getter
    @Setter
    private int matchId;

    @Getter
    @Setter
    private int firstPetId;

    @Getter
    @Setter
    private int secondPetId;

}
