package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class PetProfile implements Entity {

    @Setter
    @Getter
    private Long petProfileId;

    @Setter
    @Getter
    private Long userId;

    @Setter
    @Getter
    private String  purpose;

}
