package com.company;

public class SalesRecord {

        public static void main(String[] args) {
        final SalesRecordx customer01 = new SalesRecordx(40000, "GIF",
                "Dangotes");

    Thread thread1 = new Thread(new Runnable(){
        public void run(){
            customer01.dataInflowLedger(70);
            customer01.dataOutflowLedger(600);
        }});

            Thread thread2 = new Thread(new Runnable(){
                public void run(){
                    customer01.dataInflowLedger(90);
                    customer01.dataOutflowLedger(500);
                }});

            thread2.start();
            thread1.start();


            System.out.println("S=DAY PAID==D");

            System.out.println("balance of accout is " + customer01.ledgerFundsTracker );

}

    public static class SalesRecordx{
        private double ledgerFundsTracker;
        private String serviceName;
        private String clientHni;

        public SalesRecordx(double ledgerFundsTracker, String serviceName, String clientHni) {
            this.ledgerFundsTracker = ledgerFundsTracker;
            this.serviceName = serviceName;
            this.clientHni = clientHni;
        }

        public synchronized void dataInflowLedger(double amoutBal) {
            ledgerFundsTracker += amoutBal;
        }

        public synchronized void dataOutflowLedger(double amoutBal) {
            ledgerFundsTracker -= amoutBal;
        }

        public double getLedgerFundsTracker() {
            return ledgerFundsTracker;
        }

        public void setLedgerFundsTracker(double ledgerFundsTracker) {
            this.ledgerFundsTracker = ledgerFundsTracker;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getClientHni() {
            return clientHni;
        }

        public void setClientHni(String clientHni) {
            this.clientHni = clientHni;
        }
    }

    public SalesRecord() {
    }
}






