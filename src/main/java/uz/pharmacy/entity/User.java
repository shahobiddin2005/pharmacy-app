package uz.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pharmacy.util.Utils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"username"})
public class User {
    private final String id = Utils.generateId();
    private String name;
    private String username;
    private String password;
    private Double balance;
    private Role role;
}
