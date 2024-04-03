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
    private List<Tag> tags;

    @Override
    public String toString() {
        String result = String.format("%-16d%-16s%-16s", petProfileId, userId, purpose);
        for (Tag tag : tags) {
            result = result.concat(String.format("(%d, %s) ", tag.getTagId(), tag.getName()));
        }
        return result;
    }
}
