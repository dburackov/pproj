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
    private Long likeId;

    @Setter
    @Getter
    private Long objectPetId;

    @Setter
    @Getter
    private Long targetPetId;

    @Override
    public String toString() {
        return String.format("%-16d%-16d%-16d", likeId, objectPetId, targetPetId);
    }
}
