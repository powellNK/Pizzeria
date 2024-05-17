package application.services;

import domain.User;
import infrastructure.db.Database;

public class AccountService {
    private final Database database;

    public AccountService(Database database) {
        this.database = database;
    }

    public void printAccounts() {
        database.printAccounts();
    }

    public void printUserAccount(User user) {
        database.printAccounts(user);
    }

    public int getBalance(User user) {
        return database.getBalance(user);
    }
}
