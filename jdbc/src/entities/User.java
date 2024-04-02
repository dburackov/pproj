package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class User implements Entity {
    @Setter
    @Getter
    private Long userId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String password;

    @Override
    public String toString() {
        return String.format("%-16d%-16s%-16s%-16s", userId, name, email, password);
    }
}
