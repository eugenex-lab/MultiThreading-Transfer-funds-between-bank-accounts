package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.company.MainX.EOF;

public class MainX {

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        ReentrantLock bufferLock = new ReentrantLock();
        MyTeamJersey producer = new MyTeamJersey(buffer, ClubColors.ANSI_YELLOW,bufferLock);
        MyTeamJersey producer1 = new MyTeamJersey(buffer, ClubColors.ANSI_BLACK,bufferLock);
        MyTeamJersey producer2 = new MyTeamJersey(buffer, ClubColors.ANSI_GREEN,bufferLock);

        PlayerSponsors consumer1 = new PlayerSponsors(buffer, ClubColors.ANSI_PURPLE,bufferLock);
        PlayerSponsors consumer2 = new PlayerSponsors(buffer, ClubColors.ANSI_CYAN,bufferLock);
//        PlayerSponsors consumer2mer3 = new PlayerSponsors(buffer, ClubColors.ANSI_BLUE);
//        PlayerSponsors consumer4 = new PlayerSponsors(buffer, ClubColors.ANSI_RED);

        new Thread(producer).start();
//        new Thread(producer1).start();
//        new Thread(producer2).start();

        new Thread(consumer1).start();
        new Thread(consumer2).start();
//        new Thread(consumermer3).start();
//        new Thread(consumer4).start();

    }


}

class MyTeamJersey implements  Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyTeamJersey(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {
        Random random = new Random();
            String[] nums = { "Cr7", "Rude boi", "Berbatov", "Pogbs", "Ibra"};

        for(String num: nums) {
            try {
                System.out.println(color + "Adding United PLayer ..." + num);
                bufferLock.lock();
                try{
                    buffer.add(num);
                }finally{
                    bufferLock.unlock();
                }
                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e) {
                System.out.println("This announcement  was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exitin g....");
        bufferLock.lock();
        try{
            buffer.add("EOF");
        }finally{
            bufferLock.unlock();
        }
    }
}

class PlayerSponsors implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;


    public PlayerSponsors(List<String> buffer, String color, ReentrantLock  bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;

    }

    public void run() {

        int counter = 0;

        while (true) {
            if (bufferLock.tryLock()) {

                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color + " the diagnostic counter  = " + counter);
                    counter = 0;

                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Jersey Has fiinished please come back next time ....Program is Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            } else {
                counter++;
            }
        }
    }}
