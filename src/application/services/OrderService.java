package application.services;

import domain.Basket;
import domain.Order;
import domain.User;
import infrastructure.db.Database;

public class OrderService {
    private final Database database;

    public OrderService(Database database) {
        this.database = database;
    }

    public void printBasket() {
        database.printBasket();
    }

    public Basket createBasketPosition(String namePizza, int size) {
        return database.createBasketPosition(namePizza, size);
    }

    public void addToppingInBasket(Basket basket, String nameTopping) {
        database.addToppingInBasket(basket, nameTopping);
    }

    public void deletePizzaFromBasket(int numberPosition) {
        database.deletePizzaFromBasket(numberPosition);
    }

    public void deleteToppingFromPizza(int numberPosition, String nameTopping) {
        database.deleteToppingFromPizza(numberPosition, nameTopping);
    }

    public boolean isToppingExistsInPizza(Basket basket, String nameTopping) {
        return database.isToppingExistsInPizza(basket, nameTopping);
    }

    public void makeOrder(Order order, User user, int transferredAmount) {
        database.makeAnOrder(order, user, transferredAmount);
    }

    public int getFullPriceBasket() {
        return database.getPriceBasket();
    }

    public void printOrders() {
        database.printOrders();
    }

    public Order createOrder(User user) {
        return database.createOrder(user);
    }

    public void printOrders(User user) {
        database.printOrders(user);
    }
}
