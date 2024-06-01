package uz.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pharmacy.util.Utils;

@AllArgsConstructor()
@NoArgsConstructor
@Data
public class Pharmacy {
    private final String id = Utils.generateId();
    private String name;
    private User manager;
    private Double balance;


}
