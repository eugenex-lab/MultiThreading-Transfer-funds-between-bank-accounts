package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransferBetweenAccountsGit {

    private double balance;
    private String accountNumber;
    private String desc;

    private Lock lock = new ReentrantLock();

    public TransferBetweenAccountsGit(double balance, String accountNumber, String desc) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.desc = desc;
    }

    public boolean withdrawBalance(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean depositBalance(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += amount;
                System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean transferFunds(TransferBetweenAccountsGit destinationAcc, double amount) {
        if (withdrawBalance(amount)) {
            if (destinationAcc.depositBalance(amount)) {
                return true;
            } else {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n",
                        Thread.currentThread().getName());
                depositBalance(amount);
            }
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDesc() {
        return desc;
    }
}


class Transfer implements Runnable {
    private TransferBetweenAccountsGit sourceAccount, destinationAccount;
    private double amount;

    Transfer(TransferBetweenAccountsGit sourceAccount, TransferBetweenAccountsGit destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.transferFunds(destinationAccount, amount))
            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}



