package com.company;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class InterHouseSport100Meters {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(new InterHouseSport100Meter(ClubColors.ANSI_YELLOW), "Priority 1");
        Thread t2 = new Thread(new InterHouseSport100Meter(ClubColors.ANSI_BLUE), "Priority 700");
        Thread t3 = new Thread(new InterHouseSport100Meter(ClubColors.ANSI_GREEN), "Priority 67");
        Thread t4 = new Thread(new InterHouseSport100Meter(ClubColors.ANSI_RED), "Priority 4");

        t1.setPriority(10);
        t1.setPriority(8);
        t1.setPriority(5);
        t1.setPriority(1);

        System.out.println("100 meters competion \n10 = 10Meters");
        t3.start();
        t2.start();
        t4.start();
        t1.start();
    }


    private static class InterHouseSport100Meter implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public InterHouseSport100Meter(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                lock.lock();
                    try {
                        System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName() , runCount++);
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        System.out.println("This announcement  was interrupted");
                        System.out.println("Who will win the Interhouse sport 100m race");
                    }finally{
                        lock.unlock();}
                    // execute critial section of code
                }
            }
        }
    }