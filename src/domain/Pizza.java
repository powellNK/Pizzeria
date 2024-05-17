package domain;

public class Pizza {
    private String namePizza;
    private int price;
    private int size;

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
