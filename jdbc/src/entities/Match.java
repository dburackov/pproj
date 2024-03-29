package entities;

public class Match {
    public Match() {

    }

    public Match(int firstPetId, int secondPetId) {
        this.firstPetId = firstPetId;
        this.secondPetId = secondPetId;
    }

    public int matchId = 0;
    public int firstPetId = 0;
    public int secondPetId = 0;

}
