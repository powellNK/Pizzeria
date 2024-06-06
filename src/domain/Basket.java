package domain;

import lib.ArrayListCustom;

import java.io.Serial;
import java.io.Serializable;

public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    final private Pizza pizza;
    private ArrayListCustom<Topping> topping = new ArrayListCustom<>(1);
    private int fullPrice;

    public Basket(Pizza pizza, Topping topping) {
        this.pizza = pizza;
        this.topping = addTopping(topping);
        this.fullPrice = calculateFullPrice();
    }

    public ArrayListCustom<Topping> getTopping() {
        return topping;
    }

    private int calculateFullPrice() {
        fullPrice = 0;
        for (int i = 0; i < topping.getSize(); i++) {
            fullPrice += topping.get(i).getPrice();
        }
        fullPrice += pizza.getPrice();
        return fullPrice;
    }

    public ArrayListCustom<Topping> addTopping(Topping topping) {
        this.topping.add(topping);
        setFullPrice();
        return this.topping;
    }

    public void deleteTopping(Topping topping) {
        for (int i = 0; i < this.topping.getSize(); i++) {
            if (this.topping.get(i).equals(topping)) {
                this.topping.delete(i);
            }
        }
        setFullPrice();
    }


    public int getFullPrice() {
        return fullPrice;
    }

    private void setFullPrice() {
        this.fullPrice = calculateFullPrice();
    }

    @Override
    public String toString() {
        return "Пицца " + pizza +
                "\n     Доп. начинки: " + topping +
                "   =" + fullPrice + "р.";
    }
}
