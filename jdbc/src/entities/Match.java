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
    private int matchId;

    @Setter
    @Getter
    private int firstPetId;

    @Setter
    @Getter
    private int secondPetId;

}
