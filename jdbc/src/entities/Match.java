package entities;

public class Match {
    public Match() {

    }

    public Match(int firstPetId, int secondPetId) {
        this.firstPetId = firstPetId;
        this.secondPetId = secondPetId;
    }

    public int matchId;
    public int firstPetId;
    public int secondPetId;

}
