package com.company;

public class CashBookMain {

    public static void main(String[] args) {
        final PersonalSpendingChecker personalSpendingChecker = new PersonalSpendingChecker(1000, "8213831", "Ben");

        System.out.println("********************Account Details********************");

        Thread trThred1 = new Thread(new Runnable() {
            @Override
            public void run() {
                personalSpendingChecker.depositWallet(521.22);
                personalSpendingChecker.withdrawWallet(493);

                System.out.println("Customer Name: " + personalSpendingChecker.getDesc()
                        + "\n" +  "Customer deposits: " + personalSpendingChecker.depositWallet(810)
                        + "\n" +  "Customer withdrawal: " + personalSpendingChecker.withdrawWallet(440));
                System.out.println("Balance:= " + personalSpendingChecker.getWalletBalance());
            }
        });


        Thread trThred2 = new Thread(new Runnable() {
            @Override
            public void run() {
                personalSpendingChecker.depositWallet(521.22);
                personalSpendingChecker.withdrawWallet(493);

                System.out.println("Customer Name: " + personalSpendingChecker.getDesc()
                        + "\n" +  "Customer deposits: " + personalSpendingChecker.depositWallet(260)
                        + "\n" +  "Customer withdrawal: " + personalSpendingChecker.withdrawWallet(200));
                System.out.println("\n" );
                System.out.println("\n" );
                System.out.println("Balance:= " + personalSpendingChecker.getWalletBalance());
            }
        });

        trThred1.start();
        trThred2.start();
    }
}
