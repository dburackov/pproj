package entities;

import lombok.Getter;
import lombok.Setter;

public class Tag {
    public Tag() {};

    public Tag(String name) {
        this.name = name;
    }

    @Getter
    @Setter
    private int tagId;

    @Getter
    @Setter
    private String name;

}
