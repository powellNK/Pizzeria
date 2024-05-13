package domain;

public class Account {
    private User owner;
    private int balance;

    public Account(User owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public Account() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "owner=" + owner +
                ", balance=" + balance +
                '}';
    }
}
