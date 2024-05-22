package domain;

import lib.ArrayListCustom;

import java.io.Serial;
import java.io.Serializable;

public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    final private Pizza pizza;
    private ArrayListCustom<Topping> topping;
    private int fullPrice;

    public Basket(Pizza pizza, ArrayListCustom<Topping> topping) {
        this.pizza = pizza;
        this.topping = topping;
        this.fullPrice = calculateFullPrice();
    }

    public ArrayListCustom<Topping> getTopping() {
        return topping;
    }

    public void setTopping(ArrayListCustom<Topping> topping) {
        this.topping = topping;
    }

    public int calculateFullPrice() {
        fullPrice = 0;
        for (int i = 0; i < topping.getSize(); i++) {
            fullPrice += topping.get(i).getPrice();
        }
        fullPrice += pizza.getPrice();
        return fullPrice;

    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice() {
        this.fullPrice = calculateFullPrice();
    }

    @Override
    public String toString() {
        return "Пицца " + pizza +
                "\n     Доп. начинки: " + topping +
                "   =" + fullPrice + "р.";
    }
}
