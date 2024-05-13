package domain;

public class Topping {
    private String nameTopping;
    private int price;

    public Topping(String nameTopping, int price) {
        this.nameTopping = nameTopping;
        this.price = price;
    }

    public Topping() {
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
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

    @Override
    public String toString() {
        return nameTopping + ", " + price + 'р';
    }
}
