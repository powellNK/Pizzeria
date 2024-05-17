package domain;

import lib.ArrayListCustom;

public class Order {
    private User user;
    private ArrayListCustom<Basket> compositionOrder;
    private int price;

    public Order(User user, ArrayListCustom<Basket> compositionOrder, int price) {
        this.user = user;
        this.compositionOrder = compositionOrder;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", compositionOrder=" + compositionOrder +
                ", price=" + price +
                '}';
    }
}
