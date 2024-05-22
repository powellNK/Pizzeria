package application.services;

import domain.User;
import infrastructure.db.Database;

public class UserService {
    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }


    //по умолчанию все не админы!!
    public void createUser(String login, String phone, String email, boolean isAdmin) {
        if (!((phone.length() == 12 && phone.startsWith("+7")) || (phone.length() == 11 && phone.startsWith("8")))) {
            throw new IllegalArgumentException("Неверный номер телефона");
        }
        User createdUser = new User(login, phone, email, isAdmin);
        database.addUser(createdUser);
    }

    public User getUser(String login) {
        return database.getUser(login);
    }

    public boolean isUserExists(String login) {
        return database.isUserExists(login);
    }

    public void editUserPhone(User user, String newPhoneNumber) {
        if (!((newPhoneNumber.length() == 12 && newPhoneNumber.startsWith("+7")) || (newPhoneNumber.length() == 11 && newPhoneNumber.startsWith("8")))) {
            throw new IllegalArgumentException("Неверный номер телефона");
        }
        database.editUserPhone(user, newPhoneNumber);
    }

    public void editUserEmail(User user, String newEmail) {
        database.editUserEmail(user, newEmail);
    }
}
