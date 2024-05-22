package domain;

import java.io.Serial;
import java.io.Serializable;

public class Topping implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String nameTopping;
    private final int price;

    public Topping(String nameTopping, int price) {
        this.nameTopping = nameTopping;
        this.price = price;
    }


    public String getNameTopping() {
        return nameTopping;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return nameTopping + ", " + price + 'р';
    }
}
