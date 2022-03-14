package com.company;

public class DreadlockTwoThreadsShoutingAtEachOther {
    public static void main(String[] args) {

        final ShoutSequence citizen1 = new ShoutSequence("Ricky from trailer park screams");
        final ShoutSequence citizen2 = new ShoutSequence("Randy from trailer park");


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

        ShoutSequence(String citizen) {
            this.citizen = citizen;
        }

        public String getCitizen() {
            return citizen;
        }

        public synchronized void sayHello(ShoutSequence person1) {
            System.out.format( " Hello Randy! + \"%s\" "  +  ", fuck youuu Randy !! %n",
                    this.citizen );
            person1.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(ShoutSequence person) {
            System.out.format( " Hey Ricky! + \"%s\"  "+  " Freak off Ricky!%n" + "%n"
                    , this.citizen, person.getCitizen());
        }

    }
}
