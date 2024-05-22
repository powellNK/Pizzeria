package application.services;

import domain.Pizza;
import domain.Topping;
import infrastructure.db.Database;

public class ProductService {
    private final Database database;

    public ProductService(Database database) {
        this.database = database;
    }

    public void addPizza(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        } else {
            int size = 25;
            for (int i = 0; i < 3; i++) {
                Pizza createdPizza = new Pizza(name, price, size);
                price += 150;
                size += 5;
                database.addPizza(createdPizza);
            }
        }
    }

    public void printPizza() {
        database.printPizza();
    }

    public void printPizza(int step) {
        database.printPizza(step);
    }

    public boolean isPizzaExists(String name) {
        return database.isPizzaExists(name);
    }

    public boolean isToppingExists(String name) {
        return database.isToppingExists(name);
    }

    public void addTopping(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        } else {
            Topping createdTopping = new Topping(name, price);
            database.addTopping(createdTopping);
        }
    }


    public void deletePizza(String name) {
        database.deletePizza(name);
    }

    public void printToppings() {
        database.printToppings();
    }

    public void deleteTopping(String name) {
        database.deleteTopping(name);
    }

}

