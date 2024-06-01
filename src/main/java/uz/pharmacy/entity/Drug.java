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
    private Double price;

    @Override
    public Drug clone() throws CloneNotSupportedException {
        return (Drug) super.clone();
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pharmacy=" + pharmacy.getName() +
                ", price=" + price +
                '}';
    }
}
