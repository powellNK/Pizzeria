package infrastructure.db;

import domain.*;
import lib.ArrayListCustom;

public class Database {
    final private ArrayListCustom<User> users;
    private ArrayListCustom<Account> accounts;
    private ArrayListCustom<Topping> toppings;
    private ArrayListCustom<Basket> baskets;
    private ArrayListCustom<Order> orders;
    private ArrayListCustom<Pizza> pizzas;
    private ArrayListCustom<Topping> basketTopping;

    public Database() {
        users = new ArrayListCustom<>(20);
        accounts = new ArrayListCustom<>(20);
        toppings = new ArrayListCustom<>(5);
        baskets = new ArrayListCustom<>(5);
        orders = new ArrayListCustom<>(20);
        pizzas = new ArrayListCustom<>(20);
    }

    public void addUser(User createdUser) {
        for (int i = 0; i < users.getSize(); i++) {
            if (createdUser.equals(users.get(i))) {
                throw new IllegalArgumentException("Такой пользователь уже есть");
            }
        }
        users.add(createdUser);
        createAccount(createdUser.getLogin());
    }

    public void printUsers() {
        for (int i = 0; i < users.getSize(); i++) {
            System.out.println(users.get(i));
        }
    }

    public User getUser(String userFullName) throws IllegalArgumentException {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.get(i).getLogin().equals(userFullName)) {
                return users.get(i);
            }
        }
        throw new IllegalArgumentException("Такого пользователя не существует");
    }

    public boolean isUserExists(String loginUser) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.get(i).getLogin().equals(loginUser)) {
                return true;
            }
        }
        return false;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void printPizza() {
        for (int i = 0; i < pizzas.getSize(); i++) {
            System.out.println(pizzas.get(i));
        }
    }

    public void printPizza(int step) {
        for (int i = 0; i < pizzas.getSize(); i += step) {
            System.out.println(pizzas.get(i));
        }
    }

    public boolean isPizzaExists(String namePizza) {
        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(namePizza)) {
                return true;
            }
        }
        return false;
    }

    public boolean isToppingExists(String nameTopping) {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(nameTopping)) {
                return true;
            }
        }
        return false;
    }

    public void addTopping(Topping createdTopping) {
        toppings.add(createdTopping);
    }

    public void deletePizza(String namePizza) {

        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(namePizza)) {
                pizzas.delete(i);
                i--;
            }
        }
    }

    public void printToppings() {
        for (int i = 0; i < toppings.getSize(); i++) {
            System.out.println(toppings.get(i));
        }
    }

    public void deleteTopping(String nameTopping) {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(nameTopping)) {
                toppings.delete(i);
                i--;
            }
        }
    }


    public Topping getTopping(String nameTopping) throws IllegalArgumentException {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(nameTopping)) {
                return toppings.get(i);
            }
        }
        throw new IllegalArgumentException("Такой начинки не существует");
    }

    public void printBasket() {
        for (int i = 0; i < baskets.getSize(); i++) {
            System.out.println((i + 1) + " " + baskets.get(i));
        }
    }


    public Pizza getPizza(String pizzaName, int size) throws IllegalArgumentException {
        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(pizzaName) && pizzas.get(i).getSize() == size) {
                return pizzas.get(i);
            }
        }
        throw new IllegalArgumentException("Пицца такого размера не существует");
    }

    public Basket createBasketPosition(String namePizza, int size) {
        basketTopping = new ArrayListCustom<>(1);
        Pizza pizza = getPizza(namePizza, size);
        basketTopping.add(toppings.get(0));
        int price = calculatePrice(pizza, basketTopping);
        Basket basketPosition = new Basket(pizza, basketTopping, price);
        baskets.add(basketPosition);
        return basketPosition;
    }

    private int calculatePrice(Pizza pizza, ArrayListCustom<Topping> basketTopping) {
        int fullPrice = 0;
        for (int i = 0; i < basketTopping.getSize(); i++) {
            fullPrice += basketTopping.get(i).getPrice();
        }
        fullPrice += pizza.getPrice();
        return fullPrice;
    }

    private int calculatePrice(Basket basket) {
        int fullPrice = 0;
        for (int i = 0; i < basketTopping.getSize(); i++) {
            fullPrice += basketTopping.get(i).getPrice();
        }
        fullPrice += basket.getPizza().getPrice();
        return fullPrice;
    }

    public void addToppingInBasket(Basket basket, String nameTopping) {

        if (basketTopping.contains(toppings.get(0))) {
            deleteToppingOfBasket(toppings.get(0));
        }
        basketTopping.add(getTopping(nameTopping));
        basket.setTopping(basketTopping);
        basket.setFullPrice(calculatePrice(basket));
    }

    private void deleteToppingOfBasket(Topping topping) {
        int index = basketTopping.getIndex(topping);
        basketTopping.delete(index);
    }

    public void clearToppingAPizza() {
        for (int i = 0; i < basketTopping.getSize(); i++) {
            basketTopping.delete(i);
        }
    }

    public void createAccount(String loginOwner) {
        User owner = getUser(loginOwner);
        Account account = new Account(owner, 0);
        accounts.add(account);
    }

    public void printAccounts() {
        for (int i = 0; i < accounts.getSize(); i++) {
            System.out.println(accounts.get(i));
        }
    }

    public void printAccounts(User user) {
        for (int i = 0; i < accounts.getSize(); i++) {
            if (accounts.get(i).getOwner().equals(user)) {
                System.out.println(accounts.get(i));
            }
        }
    }

    public void editUserPhone(User user, String newPhoneNumber) {
        user.setPhoneNumber(newPhoneNumber);
    }

    public void editUserEmail(User user, String newEmail) {
        user.setEmail(newEmail);
    }

    public void deletePizzaFromBasket(int numberPosition) {
        if (numberPosition < 1 || numberPosition > baskets.getSize()) {
            System.out.println("Такой пиццы нет в корзине");
        } else {
            for (int i = numberPosition - 1; i < baskets.getSize(); i++) {
                System.out.println(baskets.get(i) + " удалена из корзины");
                baskets.delete(i);
            }
        }

    }


    public void deleteToppingFromPizza(int numberPosition, String nameTopping) {
        Basket selectedPizza = baskets.get(numberPosition - 1);
        var toppingSelectedPizza = selectedPizza.getTopping();
        for (int i = 0; i < toppingSelectedPizza.getSize(); i++) {
            if (toppingSelectedPizza.get(i).getNameTopping().equals(nameTopping)) {
                toppingSelectedPizza.delete(i);
            }
        }
        if (toppingSelectedPizza.getSize() == 0) {
            toppingSelectedPizza.add(toppings.get(0));
        }
        baskets.get(numberPosition - 1).setTopping(toppingSelectedPizza);
    }


    public boolean isToppingExistsInPizza(Basket basket, String nameTopping) {
        for (int i = 0; i < basket.getTopping().getSize(); i++) {
            if (basket.getTopping().get(i).getNameTopping().equals(nameTopping)) {
                return true;
            }
        }
        return false;
    }

    public void makeAnOrder(User user, int amountToBePaid) {
        int balance = getAccount(user).getBalance();
        int fullAmount = amountToBePaid + balance;
        if (fullAmount < getPriceBasket()) {
            throw new IllegalArgumentException("Недостаточно средств");
        } else {
            Order order = new Order(user, baskets, getPriceBasket());
            orders.add(order);
            if (amountToBePaid > getPriceBasket()) {
                balance += (amountToBePaid - getPriceBasket());
            } else {
                balance = balance + amountToBePaid - getPriceBasket();
            }
            getAccount(user).setBalance(balance);
        }
    }

    public int getPriceBasket() {
        int fullPrice = 0;
        for (int i = 0; i < baskets.getSize(); i++) {
            fullPrice += baskets.get(i).getFullPrice();
        }
        return fullPrice;
    }

    public Account getAccount(User user) throws IllegalArgumentException {
        for (int i = 0; i < accounts.getSize(); i++) {
            if (accounts.get(i).getOwner().equals(user)) {
                return accounts.get(i);
            }
        }
        throw new IllegalArgumentException("Пицца такого размера не существует");
    }

    public void clearBasket() {
        for (int i = 0; i < baskets.getSize(); i++) {
            baskets.delete(i);
            i--;
        }
    }
}

// УДАЛИТЬ ЛИШНИЕ ФУНКЦИИ
// причесать существующие до адеквата