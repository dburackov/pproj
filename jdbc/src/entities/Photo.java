package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class Photo implements Entity {

    @Setter
    @Getter
    private int photoId;

    @Setter
    @Getter
    private int petProfileId;

    @Setter
    @Getter
    private String filePath;

}
