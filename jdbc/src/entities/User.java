package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class User implements Entity {
    private int userId;

    private String name;

    private String email;

    private String password;
}
