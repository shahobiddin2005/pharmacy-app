package uz.pharmacy.entity;

import lombok.*;
import uz.pharmacy.util.Utils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"username"})
@ToString(exclude = {"username", "password"})
public class User {
    private final String id = Utils.generateId();
    private String name;
    private String username;
    private String password;
    private Double balance;
    private Role role;
}
