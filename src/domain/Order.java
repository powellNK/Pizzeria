package domain;

import lib.ArrayListCustom;

public class Order {
    private User user;
    private ArrayListCustom<Basket> compositionOrder;
    private int price;

    public Order(User user, ArrayListCustom<Basket> compositionOrder) {
        this.user = user;
        this.compositionOrder = compositionOrder;
        this.price = calculateFullPrice();
    }

    public int calculateFullPrice() {
        price = 0;
        for (int i = 0; i < compositionOrder.getSize(); i++) {
            price += compositionOrder.get(i).getFullPrice();
        }
        return price;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompositionOrder(ArrayListCustom<Basket> compositionOrder) {
        this.compositionOrder = compositionOrder;
    }

    public void setPrice() {
        this.price = calculateFullPrice();
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
