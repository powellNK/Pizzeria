package domain;

public class Transaction {
    private User owner;
    private int amount;
    private Account account;

    public Transaction(User owner, int amount, Account account) {
        this.owner = owner;
        this.amount = amount;
        this.account = account;
    }

    public Transaction() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
