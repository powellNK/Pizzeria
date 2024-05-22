package domain;

import java.io.Serial;
import java.io.Serializable;

public class Pizza implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String namePizza;
    private final int price;
    private final int size;

    public Pizza(String namePizza, int price, int size) {
        this.namePizza = namePizza;
        this.price = price;
        this.size = size;
    }


    public String getNamePizza() {
        return namePizza;
    }


    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return namePizza + ", " + price + "р, " + size + "см";
    }
}
