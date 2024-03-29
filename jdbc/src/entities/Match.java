package entities;

public class Match {
    public Match() {

    }

    public Match(int first_pet_id, int second_pet_id) {
        this.first_pet_id = first_pet_id;
        this.second_pet_id = second_pet_id;
    }

    public int match_id = 0;
    public int first_pet_id = 0;
    public int second_pet_id = 0;

}
