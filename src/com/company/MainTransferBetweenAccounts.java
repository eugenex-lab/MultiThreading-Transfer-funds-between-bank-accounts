package com.company;

public class MainTransferBetweenAccounts {

    public static void main(String[] args) {

        TransferBetweenAccountsGit acc1 = new TransferBetweenAccountsGit(900,"0918273","my sons school fees");
        TransferBetweenAccountsGit acc2 = new TransferBetweenAccountsGit(100,"2132444","investment account");

        new Thread(new Transfer(acc1,acc2,50.00),"First Transfer").start();
        new Thread(new Transfer(acc1,acc2,0.00),"Second Transfer").start();

        System.out.println( "acc one account balance is "+ acc1.getBalance());
        System.out.println(acc2.getBalance());

    }


}
