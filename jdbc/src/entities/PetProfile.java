package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class PetProfile implements Entity {

    private int petProfileId;

    private int userId;

    private String  purpose;

}
