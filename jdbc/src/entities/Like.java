package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
public class Like implements Entity {

    @Setter
    @Getter
    private int likeId;

    @Setter
    @Getter
    private int objectPetId;

    @Setter
    @Getter
    private int targetPetId;
}
