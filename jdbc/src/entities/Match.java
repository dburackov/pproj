package entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Match implements Entity {
    private int matchId;

    private int firstPetId;

    private int secondPetId;

}
