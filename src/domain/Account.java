package domain;

import java.io.Serial;
import java.io.Serializable;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final User owner;
    private int balance;

    public Account(User owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return owner +
                ", Баланс: " + balance;
    }
}
