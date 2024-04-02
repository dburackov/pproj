package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
public class Match implements Entity {
    @Setter
    @Getter
    private Long matchId;

    @Setter
    @Getter
    private Long firstPetId;

    @Setter
    @Getter
    private Long secondPetId;

    @Override
    public String toString() {
        return String.format("%-16d%-16d%-16d", matchId, firstPetId, secondPetId);
    }
}
