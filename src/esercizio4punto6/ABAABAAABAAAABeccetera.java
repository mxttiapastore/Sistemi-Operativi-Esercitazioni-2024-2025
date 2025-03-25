package esercizio4punto6;

import java.util.concurrent.Semaphore;

public class ABAABAAABAAAABeccetera {


    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);

    private static int ripetizione = 1;



    static class A extends Thread{

        public void run(){
            try{
                semA.acquire();

                for(int i = 0 ; i < ripetizione ; i++){
                    System.out.print("A");
                }
                ripetizione++;

                semB.release();

            }catch (InterruptedException e){e.printStackTrace();}
        }
    }

    static class B extends Thread{

        public void run(){
            try{
                semB.acquire();

                System.out.print("B");

                semA.release();

            }catch(InterruptedException e){e.printStackTrace();}
        }
    }


    public static void main(String[] args) {
        try{
            while(true) {
                new A().start();
                new B().start();

                Thread.sleep(1000);
            }
        }catch (InterruptedException e){e.printStackTrace();}

    }
}
