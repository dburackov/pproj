package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class ViewedProfile implements Entity {

    @Setter
    @Getter
    private Long viewedProfileId;

    @Setter
    @Getter
    private Long objectPetId;

    @Setter
    @Getter
    private Long targetPetId;

    @Override
    public String toString() {
        return String.format("%-16d%-16d%-16d", viewedProfileId, objectPetId, targetPetId);
    }
}
