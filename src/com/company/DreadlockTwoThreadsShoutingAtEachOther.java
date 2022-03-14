package com.company;

public class DreadlockTwoThreadsShoutingAtEachOther {
    public static void main(String[] args) {

        final ShoutSequence citizen1 = new ShoutSequence("Ricky from trailer park screams"  ,ClubColors.ANSI_GREEN );
        final ShoutSequence citizen2 = new ShoutSequence("Randy from trailer park" , ClubColors.ANSI_BLUE );


        new Thread(new Runnable() {
            @Override
            public void run() {
                citizen1.sayHello(citizen2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                citizen2.sayHello(citizen1);
            }
        }).start();
    }



    static class ShoutSequence{
        private final String citizen;
        private String color;

        ShoutSequence(String citizen, String color) {
            this.citizen = citizen;
            this.color = color;
        }

        public String getCitizen() {
            return citizen;
        }

        public synchronized void sayHello(ShoutSequence person1) {
            System.out.format( color + " Hello Randy! + \"%s\" "  +  ", fuck youuu Randy !! %n",
                    this.citizen );
            person1.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(ShoutSequence person) {
            System.out.format( color +  " Hey Ricky! + \"%s\"  "+  " Freak off Ricky!%n" + "%n"
                    , this.citizen, person.getCitizen());
        }

    }
}
