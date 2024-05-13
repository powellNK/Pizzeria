package application.services;

import domain.Basket;
import infrastructure.db.Database;

public class OrderService {
    private Database database;

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

    public void clearToppingAPizza() {
        database.clearToppingAPizza();
    }

    public void deletePizzaFromBasket(int numberPosition) {
        database.deletePizzaFromBasket(numberPosition);
    }

    public void deleteToppingFromPizza(int numberPosition, String nameTopping) {
        database.deleteToppingFromPizza(numberPosition, nameTopping);
    }
}