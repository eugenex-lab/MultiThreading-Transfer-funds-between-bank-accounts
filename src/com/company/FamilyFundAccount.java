package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FamilyFundAccount {

    private double walletBalance;
    private String accountId;
    private String desc;

    private Lock lock = new ReentrantLock();

    public FamilyFundAccount(double walletBalance, String accountId, String desc) {
        this.walletBalance = walletBalance;
        this.accountId = accountId;
        this.desc = desc;
    }


    public boolean depositWallet(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                walletBalance += amount;
                System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName() + amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }


    public boolean withdrawWallet(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                walletBalance -= amount;
                System.out.printf("%s: Withdrawn %f\n", Thread.currentThread().getName() + amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public double getWalletBalance() {
        synchronized (this) {
            return walletBalance;
        }
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean transfer(FamilyFundAccount destinationAccount, double amount) {
        if (withdrawWallet(amount)) {
            if (destinationAccount.depositWallet(amount)) {
                return true;
            } else {
                System.out.printf("%s: Destination account busy. Refunding money\n",
                        Thread.currentThread().getName());
                depositWallet(amount);
            }
        }
        return false;
    }
}
