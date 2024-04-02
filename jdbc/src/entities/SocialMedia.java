package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
public class SocialMedia implements Entity {

    @Setter
    @Getter
    private Long socialMediaId;

    @Setter
    @Getter
    private Long petProfileId;

    @Setter
    @Getter
    private String link;

    @Setter
    @Getter
    private String type;

}
