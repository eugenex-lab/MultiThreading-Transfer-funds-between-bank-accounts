package com.company;

public class MainAccountRun {

    public static void main(String[] args) {
        FamilyFundAccount account1 = new FamilyFundAccount(30000, "27373yt33","scholl fees");
        FamilyFundAccount account2 = new FamilyFundAccount(80000, "821213312","soccer practice");

        new Thread(new TransferToFamilyTrustAccount(account1, account2, 10.00), "Transfer1").start();
        new Thread(new TransferToFamilyTrustAccount(account2, account1, 55.88), "Transfer2").start();
    }
}

class TransferToFamilyTrustAccount implements Runnable {

    private FamilyFundAccount sourceAccount,  destinationAccount;
    private double amount;

    public TransferToFamilyTrustAccount(FamilyFundAccount sourceAccount,
                                        FamilyFundAccount destinationAccount,
                                        double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}
