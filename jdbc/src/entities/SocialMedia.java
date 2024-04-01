package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class SocialMedia implements Entity {

    private int socialMediaId = 0;

    private int petProfileId = 0;

    private String link = null;

    private String type = null;

}
