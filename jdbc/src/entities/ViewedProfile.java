package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ViewedProfile implements Entity {

    private int viewedProfileId = 0;

    private int objectPetId = 0;

    private int targetPetId = 0;

}
