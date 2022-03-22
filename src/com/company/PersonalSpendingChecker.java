package com.company;

public class PersonalSpendingChecker {

    private double walletBalance = 1000;
    private String accountId;
    private String desc;

    public PersonalSpendingChecker(double walletBalance, String accountId , String desc) {
        this.walletBalance = walletBalance;
        this.accountId = accountId;
        this.desc = desc;
    }


    public double depositWallet(double amount ) {
        synchronized (this) {
            this.walletBalance += amount;
            return amount;
        }
    }

    public double withdrawWallet(double amount) {
        synchronized (this) {
        this.walletBalance -= amount;
        return amount;
    }}

    public double getWalletBalance() {
        synchronized (this) {
        return walletBalance ;
    }}

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
}
