package domain;

import lib.ArrayListCustom;

public class Basket {
    private Pizza pizza;
    private ArrayListCustom<Topping> topping;
    private int fullPrice;

    public Basket(Pizza pizza, ArrayListCustom<Topping> topping) {
        this.pizza = pizza;
        this.topping = topping;
        this.fullPrice = calculateFullPrice();
    }

    public Pizza getPizza() {
        return pizza;
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
        return "Basket{" +
                "Pizza=" + pizza +
                ", topping=" + topping +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
