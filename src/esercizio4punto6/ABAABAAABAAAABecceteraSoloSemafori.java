package esercizio4punto6;

import java.util.concurrent.Semaphore;

public class ABAABAAABAAAABecceteraSoloSemafori {

    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);

    static class A extends Thread{

        public void run(){

            try{
                semA.acquire();
                System.out.println("A");
                semB.release();
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }

    static class B extends Thread{

        public void run(){

            try{
                semB.acquire();
                System.out.println("B");

            }catch (InterruptedException e){e.printStackTrace();}
        }
    }
}
