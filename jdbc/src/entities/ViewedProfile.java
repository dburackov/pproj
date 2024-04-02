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
    private int viewedProfileId = 0;

    @Setter
    @Getter
    private int objectPetId = 0;

    @Setter
    @Getter
    private int targetPetId = 0;

}
