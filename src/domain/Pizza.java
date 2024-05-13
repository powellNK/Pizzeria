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

    public Pizza() {
    }

    public String getNamePizza() {
        return namePizza;
    }

    public void setNamePizza(String namePizza) {
        this.namePizza = namePizza;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return namePizza + ", " + price + "р, " + size + "см";
    }
}
