package uz.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pharmacy.util.Utils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Drug implements Cloneable {
    private final String id = Utils.generateId();
    private String name;
    private Pharmacy pharmacy;
    private User owner;

}
