package domain;

import lib.ArrayListCustom;

public class Basket {
    private Pizza pizza;
    private ArrayListCustom<Topping> topping;
    private int fullPrice;

    public Basket(Pizza pizza, ArrayListCustom<Topping> topping, int fullPrice) {
        this.pizza = pizza;
        this.topping = topping;
        this.fullPrice = fullPrice;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public ArrayListCustom<Topping> getTopping() {
        return topping;
    }

    public void setTopping(ArrayListCustom<Topping> topping) {
        this.topping = topping;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
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
