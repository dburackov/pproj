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
    private Long photoId;

    @Setter
    @Getter
    private Long petProfileId;

    @Setter
    @Getter
    private String filePath;

}
