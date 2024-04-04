package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
public class PetProfile implements Entity {

    @Setter
    @Getter
    private Long petProfileId;

    @Setter
    @Getter
    private Long userId;

    @Setter
    @Getter
    private String  purpose;

    @Setter
    @Getter
    private List<Long> tags;

    @Override
    public String toString() {
        String result = String.format("%-16d%-16s%-16s", petProfileId, userId, purpose);
        result = result.concat(tags.toString());
        return result;
    }
}
