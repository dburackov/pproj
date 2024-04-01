package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class Tag implements Entity {

    private int tagId;

    private String name;

}
