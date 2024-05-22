package domain;

import lib.ArrayListCustom;

import java.io.Serial;
import java.io.Serializable;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final User user;
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

    public void setCompositionOrder(ArrayListCustom<Basket> compositionOrder) {
        this.compositionOrder = compositionOrder;
    }

    public void setPrice() {
        this.price = calculateFullPrice();
    }

    @Override
    public String toString() {
        return "Пользователь: " + user.getLogin() +
                "\nЗаказ: \n" + compositionOrder +
                "\nОбщая сумма чека " + price +
                "р.";
    }
}
