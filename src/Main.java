import application.services.AccountService;
import application.services.OrderService;
import application.services.ProductService;
import application.services.UserService;
import domain.Basket;
import domain.User;
import infrastructure.db.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        Database db = new Database();
        UserService userService = new UserService(db);
        ProductService productService = new ProductService(db);
        OrderService orderService = new OrderService(db);
        AccountService accountService = new AccountService(db);


        userService.createUser("olya", "+79532844890", "89803160948v@gmail.com", true);
        userService.createUser("prob", "+71112844890", "11803160948v@gmail.com", false);
        userService.createUser("Юрий Евсеев", "+79203335455", "dsadsadas", false);
        userService.createUser("Смыслов Алексей", "+79603331122", "dsadsadas", false);
        userService.createUser("Березуцкий", "+79604445533", "dsadsadas", false);

        productService.addPizza("Сырная", 299);
        productService.addPizza("Песто", 539);
        productService.addPizza("Пепперони", 489);
        productService.addPizza("Гавайская", 489);

        productService.addTopping("Без начинки", 0);
        productService.addTopping("Бекон", 79);
        productService.addTopping("Моцарелла", 79);
        productService.addTopping("Шампиньоны", 59);
        productService.addTopping("Халапеньо", 59);
        productService.addTopping("Ветчина", 79);
        productService.addTopping("Томаты", 59);
        productService.addTopping("Маслины", 59);


        orderService.createBasketPosition("Сырная", 25);
        orderService.createBasketPosition("Песто", 30);
        orderService.addToppingInBasket(orderService.createBasketPosition("Песто", 30), "Бекон");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        User user = authorization(reader, userService);

        String inputCommand;
        if (user.isAdmin()) {
            do {
                System.out.println("1. Список пользователей");
                System.out.println("2. Изменить меню");
                System.out.println("3. Заказы");
                System.out.println("4. Статистика");
                System.out.println("5. Вывести меню");
                inputCommand = reader.readLine();
                switch (inputCommand) {
                    case "1": {
                        userService.printUsers();
                        accountService.printAccounts();
                        break;
                    }
                    case "2": {
                        System.out.println("1. Добавить пиццу");
                        System.out.println("2. Удалить пиццу");
                        System.out.println("3. Добавить начинку");
                        System.out.println("4. Удалить начинку");
                        inputCommand = reader.readLine();
                        switch (inputCommand) {
                            //добавить пиццу
                            case "1": {
                                addPizzaToMenu(reader, productService);
                                break;
                            }

                            //удалить пиццу из меню
                            case "2": {
                                productService.printPizza(3);
                                deletePizzaFromMenu(reader, productService);
                                break;
                            }

                            //добавить начинку
                            case "3": {
                                addToppingToMenu(reader, productService);
                                break;
                            }

                            //удалить начинку
                            case "4": {
                                productService.printToppings();
                                deleteToppingFromMenu(reader, productService);
                                break;
                            }

                            default: {
                                System.out.println("Неверная команда");
                                break;
                            }
                        }
                        break;
                    }


                    //Добавить кейсы 3,4!!


                    case "5": {
                        printThePizzeriaMenu(productService);
                        break;
                    }
                    case "stop": {
                        System.out.println("Работа программы успешно завершена");
                    }
                }
            } while (!inputCommand.equals("stop"));
        } else {
            do {
                System.out.println("1. Меню");
                System.out.println("2. Профиль");
                System.out.println("3. Добавить пиццу в корзину"); //дальше выбрать размер и начинку
                System.out.println("4. Содержимое корзины");
                inputCommand = reader.readLine();
                switch (inputCommand) {
                    case "1": {
                        printThePizzeriaMenu(productService);
                        break;
                    }
                    case "2": {

                        //Добавить возможность просмотреть свою историю заказов??

                        accountService.printUserAccount(user);
                        do {
                            System.out.println("Изменить данные пользователя? (yes/no)");
                            inputCommand = reader.readLine();
                            if (inputCommand.equals("yes")) {
                                System.out.println("1. Изменить номер телефона");
                                System.out.println("2. Изменить email");
                                System.out.println("3. Выйти");
                                String command = reader.readLine();
                                switch (command) {
                                    //изменить номер телефона

                                    case "1": {
                                        System.out.println("Введите новый номер телефона");
                                        String newPhoneNumber = reader.readLine();
                                        try {
                                            userService.editUserPhone(user, newPhoneNumber);
                                        } catch (Exception exception) {
                                            System.out.println(exception.getMessage());
                                        }
                                        break;
                                    }

                                    //изменить email
                                    case "2": {
                                        System.out.println("Введите новый email");
                                        String newEmail = reader.readLine();
                                        userService.editUserEmail(user, newEmail);
                                        break;
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        } while (inputCommand.equals("yes"));

                        break;
                    }


                    //добавить пиццу в корзину
                    case "3": {
                        System.out.println("Введите название");
                        String namePizza = reader.readLine();
                        boolean isPizzaExists = productService.isPizzaExists(namePizza);
                        if (!isPizzaExists) {
                            System.out.println("Такой пиццы нет");
                            break;
                        }

                        //проверку на соответствие размеру добавить (try catch)
                        System.out.println("Выберите размер:");
                        System.out.println("25     30      35");
                        int size = Integer.parseInt(reader.readLine());
                        Basket basket = orderService.createBasketPosition(namePizza, size);

                        do {
                            System.out.println("Добавить начинку? (yes/no)");
                            inputCommand = reader.readLine();
                            if (inputCommand.equals("yes")) {
                                productService.printToppings();
                                System.out.println("Введите название");
                                String nameTopping = reader.readLine();

                                boolean isToppingExistsInBasket = orderService.isToppingExistsInPizza(basket, nameTopping);
                                if (isToppingExistsInBasket) {
                                    System.out.println("Такая начинка уже добавлена!");
                                } else {
                                    orderService.addToppingInBasket(basket, nameTopping);
                                }

                            }
                            orderService.printBasket();
                        } while (inputCommand.equals("yes"));
//                        orderService.clearToppingAPizza();


                        //Здесь должна формироваться корзина

                        break;
                    }

                    case "4": { //корзина
                        orderService.printBasket();
                        System.out.println("1. Сделать заказ");
                        System.out.println("2. Удалить пиццу");
                        System.out.println("3. Удалить начинку");
                        System.out.println("4. Выйти");


                        //Добавить ===== Добавить начинку к выбранной пицце!

                        String command = reader.readLine();
                        switch (command) {
                            case "1": {  //добавить try catch
                                orderService.printBasket();
                                System.out.println("Введите сумму: ");
                                int amountToBePaid = Integer.parseInt(reader.readLine());
                                orderService.makeOrder(user, amountToBePaid);
                                System.out.println("Заказ сделан");
                                orderService.ClearBasket();
                                break;
                            }
                            case "2": {
                                orderService.printBasket();
                                System.out.println("Введите номер пиццы");
                                int numberPosition = Integer.parseInt(reader.readLine());
                                orderService.deletePizzaFromBasket(numberPosition);
                                break;
                            }
                            case "3": {
                                //ДОБАВИТЬ try catch, если не найдены
                                orderService.printBasket();
                                System.out.println("Введите номер пиццы");
                                int numberPosition = Integer.parseInt(reader.readLine());
                                System.out.println("Введите начинку");
                                String nameTopping = reader.readLine();
                                try {
                                    orderService.deleteToppingFromPizza(numberPosition, nameTopping);
                                } catch (Exception exception) {
                                    System.out.println(exception.getMessage());
                                }
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            } while (!inputCommand.equals("stop"));

        }


    }

    private static void addPizzaToMenu(BufferedReader reader, ProductService productService) throws IOException {
        System.out.println("Введите название: ");
        String namePizza = reader.readLine();
        boolean isPizzaExists = productService.isPizzaExists(namePizza);
        if (isPizzaExists) {
            System.out.println("Такая пицца уже существует");
            return;
        }

        System.out.println("Введите цену: ");
        int pricePizza = Integer.parseInt(reader.readLine());
        try {
            productService.addPizza(namePizza, pricePizza);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deleteToppingFromMenu(BufferedReader reader, ProductService productService) throws IOException {
        System.out.println("Введите название: ");
        String nameTopping = reader.readLine();
        boolean isToppingExists = productService.isToppingExists(nameTopping);
        if (!isToppingExists) {
            System.out.println("Такой начинки нет");
            return;
        }
        productService.deleteTopping(nameTopping);
    }

    private static void addToppingToMenu(BufferedReader reader, ProductService productService) throws IOException {
        System.out.println("Введите начинку: ");
        String nameTopping = reader.readLine();
        boolean isToppingExists = productService.isToppingExists(nameTopping);
        if (isToppingExists) {
            System.out.println("Такая начинка уже существует");
            return;
        }
        System.out.println("Введите цену: ");
        int priceTopping = Integer.parseInt(reader.readLine());
        try {
            productService.addTopping(nameTopping, priceTopping);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deletePizzaFromMenu(BufferedReader reader, ProductService productService) throws IOException {
        System.out.println("Введите название: ");
        String namePizza = reader.readLine();
        boolean isPizzaExists = productService.isPizzaExists(namePizza);
        if (!isPizzaExists) {
            System.out.println("Такой пиццы нет");
            return;
        }
        productService.deletePizza(namePizza);
    }

    private static void printThePizzeriaMenu(ProductService productService) {
        System.out.println("ПИЦЦА");
        productService.printPizza();
        System.out.println("НАЧИНКИ");
        productService.printToppings();
    }

    private static User authorization(BufferedReader reader, UserService userService) {
        String commandMenu;
        String loginUser = "";
        boolean isUserExists = false;
        do {
            System.out.println("1. Авторизация");
            System.out.println("2. Регистрация");
            try {
                commandMenu = reader.readLine();
                switch (commandMenu) {
                    case "1": {
                        System.out.println("Введите логин");
                        loginUser = reader.readLine();
                        isUserExists = userService.isUserExists(loginUser);
                        if (!isUserExists) {
                            System.out.println("Такого пользователя еще нет");
                            break;
                        }
                        System.out.println(loginUser + " вы успешно авторизованы!");
                        break;
                    }
                    case "2": {
                        System.out.println("Введите логин: ");
                        System.out.println("Введите номер телефона: ");
                        System.out.println("Введите e-mail: ");
                        loginUser = reader.readLine();
                        String phoneUser = reader.readLine();
                        String emailUser = reader.readLine();
                        try {
                            userService.createUser(loginUser, phoneUser, emailUser, false);

                            isUserExists = userService.isUserExists(loginUser);
                            System.out.println("Регистрация прошла успешно");
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    }
                    default: {
                        System.out.println("неверная команда");
                        break;
                    }

                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!isUserExists);
        return userService.getUser(loginUser);
    }

}
