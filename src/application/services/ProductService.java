package application.services;

import domain.Pizza;
import domain.Topping;
import infrastructure.db.Database;

public class ProductService {
    private Database database;

    public ProductService(Database database) {
        this.database = database;
    }

    public void addPizza(String namePizza, int pricePizza) {
        if (pricePizza < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        } else {
            int size = 25;
            for (int i = 0; i < 3; i++) {
                Pizza createdPizza = new Pizza(namePizza, pricePizza, size);
                pricePizza += 150;
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

    public boolean isPizzaExists(String namePizza) {
        return database.isPizzaExists(namePizza);
    }

    public boolean isToppingExists(String nameTopping) {
        return database.isToppingExists(nameTopping);
    }

    public void addTopping(String nameTopping, int priceTopping) {
        if (priceTopping < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        } else {
            Topping createdTopping = new Topping(nameTopping, priceTopping);
            database.addTopping(createdTopping);
        }
    }


    public void deletePizza(String namePizza) {
        database.deletePizza(namePizza);
    }

    public void printToppings() {
        database.printToppings();
    }

    public void deleteTopping(String nameTopping) {
        database.deleteTopping(nameTopping);
    }


    public Pizza getPizza(String pizzaName) {
        return database.getPizza(pizzaName);
    }
}

