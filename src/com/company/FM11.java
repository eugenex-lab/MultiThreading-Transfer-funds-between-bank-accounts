package com.company;

public class FM11 {

    public static void main(String[] args) {
        final Coaches tutor = new Coaches();
        final SoccerPlayers players = new SoccerPlayers(tutor);
        tutor.setSoccerPlayers(players);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                players.startTraining();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                players.trainingGuide();
            }
        });

        tutorThread.start();
        studentThread.start();
    }

}

class Coaches {
    private SoccerPlayers soccerPlayers;

    public synchronized void setSoccerPlayers(SoccerPlayers soccerPlayers) {
        this.soccerPlayers = soccerPlayers;
    }

    public synchronized void trainingTime() {
        System.out.println("Epl players have arrived");
        try {
            // wait for student to arrive and hand in assignment
            Thread.sleep(300);
        }
        catch (InterruptedException e) {
        }
        soccerPlayers.startTraining();
        System.out.println("Coaches are in session with players");
    }
    public synchronized void getEplStats() {
        System.out.println("Coaching team gave progress report");
    }
}

class SoccerPlayers {

    private Coaches coaches;

    SoccerPlayers(Coaches coaches) {
        this.coaches = coaches;
    }

    public synchronized void startTraining() {
        // study
        System.out.println("Team rPlayers are Training ");
    }

    public synchronized void trainingGuide() {
        coaches.getEplStats();
        System.out.println("Game stats appearance to goal ratio");
    }
}

