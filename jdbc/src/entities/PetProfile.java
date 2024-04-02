package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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

    @Setter
    @Getter
    private List<Tag> tags;
}
