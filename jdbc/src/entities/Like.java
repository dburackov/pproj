package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Like implements Entity {

    private int likeId;

    private int objectPetId;

    private int targetPetId;
}
