package domain;

public class Topping {
    private String nameTopping;
    private int price;

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
