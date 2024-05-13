package application.services;

import domain.User;
import infrastructure.db.Database;

public class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }


    //по умолчанию все не админы!!
    public void createUser(String loginUser, String phoneUser, String emailUser, boolean isAdmin) {
        if (!((phoneUser.length() == 12 && phoneUser.startsWith("+7")) || (phoneUser.length() == 11 && phoneUser.startsWith("8")))) {
            throw new IllegalArgumentException("Неверный номер телефона");
        }
        User createdUser = new User(loginUser, phoneUser, emailUser, isAdmin);
        database.addUser(createdUser);
    }

    public void printUsers() {
        database.printUsers();
    }

    public User getUser(String userLogin) {
        return database.getUser(userLogin);
    }

    public boolean isUserExists(String loginUser) {
        return database.isUserExists(loginUser);
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
