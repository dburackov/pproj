package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Entity {

    @Setter
    @Getter
    private Long tagId;

    @Setter
    @Getter
    private String name;

}
