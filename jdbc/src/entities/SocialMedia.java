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
    private int socialMediaId = 0;

    @Setter
    @Getter
    private int petProfileId = 0;

    @Setter
    @Getter
    private String link = null;

    @Setter
    @Getter
    private String type = null;

}
