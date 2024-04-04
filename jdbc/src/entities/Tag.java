package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Entity {

    @Setter
    @Getter
    private Long tagId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private List<Long> petProfiles;

    @Override
    public String toString() {
        String result = String.format("%-16d%-16s", tagId, name);
        result = result.concat(petProfiles.toString());
        return result;
    }
}
