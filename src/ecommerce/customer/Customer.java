package ecommerce.customer;

public class Customer {
    final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance -= amount;
    }
}