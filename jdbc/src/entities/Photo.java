package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Photo implements Entity {

    private int photoId;

    private int petProfileId;

    private String filePath;

}
