package infrastructure.db;

import domain.*;
import lib.ArrayListCustom;

import java.io.*;

public class Database implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayListCustom<User> users;
    private ArrayListCustom<Account> accounts;
    private ArrayListCustom<Topping> toppings;
    private ArrayListCustom<Basket> baskets;
    private ArrayListCustom<Order> orders;
    private ArrayListCustom<Pizza> pizzas;

    public Database() {
        users = new ArrayListCustom<>(20);
        accounts = new ArrayListCustom<>(20);
        toppings = new ArrayListCustom<>(5);
        orders = new ArrayListCustom<>(20);
        pizzas = new ArrayListCustom<>(20);
    }

    public void setUsers(ArrayListCustom<User> users) {
        this.users = users;
    }

    public void setAccounts(ArrayListCustom<Account> accounts) {
        this.accounts = accounts;
    }

    public void setToppings(ArrayListCustom<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setOrders(ArrayListCustom<Order> orders) {
        this.orders = orders;
    }

    public void setPizzas(ArrayListCustom<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void saveDB() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db.serialization"))) {
            oos.writeObject(users);
            oos.writeObject(accounts);
            oos.writeObject(toppings);
            oos.writeObject(orders);
            oos.writeObject(pizzas);
        } catch (IOException exception) {
            System.out.println("Не удалось сохранить данные");
        }
    }

    public void addUser(User createdUser) {
        for (int i = 0; i < users.getSize(); i++) {
            if (createdUser.equals(users.get(i))) {
                throw new IllegalArgumentException("Такой пользователь уже есть");
            }
        }
        users.add(createdUser);
        createAccount(createdUser.getLogin());

        saveDB();
    }

    public User getUser(String login) throws IllegalArgumentException {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                return users.get(i);
            }
        }
        throw new IllegalArgumentException("Такого пользователя не существует");
    }

    public boolean isUserExists(String login) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        saveDB();
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

    public boolean isPizzaExists(String name) {
        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isToppingExists(String name) {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addTopping(Topping createdTopping) {
        toppings.add(createdTopping);
        saveDB();
    }

    public void deletePizza(String name) {

        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(name)) {
                pizzas.delete(i);
                i--;
            }
        }
        saveDB();
    }

    public void printToppings() {
        for (int i = 0; i < toppings.getSize(); i++) {
            System.out.println(toppings.get(i));
        }
    }

    public void deleteTopping(String name) {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(name)) {
                toppings.delete(i);
                i--;
            }
        }
        saveDB();
    }


    public Topping getTopping(String name) throws IllegalArgumentException {
        for (int i = 0; i < toppings.getSize(); i++) {
            if (toppings.get(i).getNameTopping().equals(name)) {
                return toppings.get(i);
            }
        }
        throw new IllegalArgumentException("Такой начинки не существует");
    }

    public void printBasket() {
        if (baskets.getSize() == 0) {
            throw new IllegalArgumentException("Корзина пуста!");
        }
        for (int i = 0; i < baskets.getSize(); i++) {
            System.out.println(STR."\{i + 1} \{baskets.get(i)}");
        }
    }


    public Pizza getPizza(String name, int size) throws IllegalArgumentException {
        for (int i = 0; i < pizzas.getSize(); i++) {
            if (pizzas.get(i).getNamePizza().equals(name) && pizzas.get(i).getSize() == size) {
                return pizzas.get(i);
            }
        }
        throw new IllegalArgumentException("Пицца такого размера не существует");
    }

    public Basket createBasketPosition(String name, int size) {
        Pizza pizza = getPizza(name, size);
        Basket basketPosition = new Basket(pizza, toppings.get(0));
        baskets.add(basketPosition);
        saveDB();
        return basketPosition;

    }

    public void addToppingInBasket(Basket basket, String nameTopping) {
        basket.addTopping(getTopping(nameTopping));
        if (basket.getTopping().contains(toppings.get(0))) {
            basket.getTopping().delete(0);
        }
        saveDB();
    }


    public void createAccount(String loginOwner) {
        User owner = getUser(loginOwner);
        Account account = new Account(owner, 0);
        accounts.add(account);
        saveDB();
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
        saveDB();
    }

    public void editUserEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        saveDB();
    }

    public void deletePizzaFromBasket(int numberPosition) {

        for (int i = numberPosition - 1; i < baskets.getSize(); i++) {
            System.out.println(STR."\{baskets.get(i)} удалена из корзины");
            baskets.delete(i);
        }

        saveDB();

    }


    public void deleteToppingFromPizza(int numberPosition, String nameTopping) {
        Basket selectedPizza = getPizzaFromBasket(numberPosition);
        if (!selectedPizza.getTopping().contains(getTopping(nameTopping))) {
            throw new IllegalArgumentException("Нет такой начинки");
        }
        selectedPizza.deleteTopping(getTopping(nameTopping));
        if (selectedPizza.getTopping().getSize() == 0) {
            selectedPizza.addTopping(toppings.get(0));
        }
        saveDB();
    }

    public boolean isToppingExistsInPizza(Basket basket, String nameTopping) {
        for (int i = 0; i < basket.getTopping().getSize(); i++) {
            if (basket.getTopping().get(i).getNameTopping().equals(nameTopping)) {
                return true;
            }
        }
        return false;
    }

    public void makeAnOrder(Order order, User user, int transferredAmount) {
        int fullPriceBasket = getPriceBasket();
        int balance = getAccount(user).getBalance();
        if (transferredAmount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной!");
        } else if (transferredAmount + balance < fullPriceBasket) {
            throw new IllegalArgumentException("Недостаточно средств");
        } else {
            order.setCompositionOrder(baskets);
            order.setPrice();
            orders.add(order);
            balance += (transferredAmount - fullPriceBasket);
            System.out.println(STR."Заказ сделан. Баланс вашего бонусного счета составляет: \{balance}р.");
            getAccount(user).setBalance(balance);
        }
        saveDB();
    }


    //полная стоиость корзины
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
        throw new IllegalArgumentException("Такого счета не существует");
    }


    public int getBalance(User user) {
        return getAccount(user).getBalance();
    }

    public void printOrders() {
        for (int i = 0; i < orders.getSize(); i++) {
            System.out.println(orders.get(i));
            System.out.println();
        }
    }

    public void printOrders(User user) {
        for (int i = 0; i < orders.getSize(); i++) {
            if (orders.get(i).getUser().equals(user)) {
                System.out.println(orders.get(i));
            }
        }
    }

    public Order createOrder(User user) {
        baskets = new ArrayListCustom<>(5);
        return new Order(user, baskets);
    }

    public boolean isPizzaExistsInBasket(int numberPosition) {
        return numberPosition >= 1 && numberPosition <= baskets.getSize();
    }

    public Basket getPizzaFromBasket(Integer numberPosition) {
        return baskets.get(numberPosition - 1);
    }
}
