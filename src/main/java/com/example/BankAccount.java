package com.example;

public class BankAccount {

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("A befizetett összegnek pozitívnak és végesnek kell lennie");
        }

        balance += amount;
    }

    public void withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("A felvett összegnek pozitívnak és végesnek kell lennie");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Nincs elegendő pénz a számlán");
        }

        balance -= amount;
    }
}
