package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@NoArgsConstructor
@AllArgsConstructor
public class Like implements Entity {

    @Setter
    @Getter
    private Long likeId;

    @Setter
    @Getter
    private Long objectPetId;

    @Setter
    @Getter
    private Long targetPetId;
}
