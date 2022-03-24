package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Message message = new Message();
        (new Thread(new WriterSys(message))).start();
        (new Thread(new ReaderSys (message))).start();
   
    }


    static class Message{
        private boolean empty = true;
        private String harryPotterMemoir;

        public synchronized String understand(){
            while (empty){
                try {
                    wait();
                    System.out.println("waiting hahaahahaaahaha");
                }catch (InterruptedException e){
                    System.out.println("Execption here");
                }
            }
            empty = true;
            notifyAll();
            return harryPotterMemoir;
        }

        public synchronized void writingUp(String harryPotterMemoir){
            while (!empty){
                try {
                    wait();
                }catch (InterruptedException e){
                    System.out.println("Execption here");
                }
            }
            empty = false;
            this.harryPotterMemoir = harryPotterMemoir;
            notifyAll();
        }
    }

    static class WriterSys implements Runnable  {
        private Message harryPotterrMemoir ;

        public WriterSys(Message harryPotterrMemoir){
            this.harryPotterrMemoir = harryPotterrMemoir;
        }

        public void run() {
            String messagBody[] = {
                    "My Name is Potter, Harry Potter" ,
                    "Experus Patoronus"    ,
                    "Born with a purpose"
            };

            Random random = new Random();

            for(int i = 0; i < messagBody.length;i++){
                harryPotterrMemoir.writingUp(messagBody[i]);
                try{
                    Thread.sleep(random.nextInt(10000));
                } catch (InterruptedException e) {
                }
            }
            harryPotterrMemoir.writingUp("Done Writing");
        }
    }

    static class ReaderSys implements Runnable{

        private Message harryPotterrMemoir;

        public ReaderSys(Message harryPotterrMemoir) {
            this.harryPotterrMemoir = harryPotterrMemoir;
        }


        @Override
        public void run() {
            Random random = new Random();

            for(String newMessage = harryPotterrMemoir.understand(); !newMessage.equals("Done Writing");
                newMessage = harryPotterrMemoir.understand()){
                System.out.println(newMessage);
                try {
                    Thread.sleep(random.nextInt(3000));
                }catch (InterruptedException e){
                    System.out.println("who woke me up with error");
                }
            }

        }
    }







}


