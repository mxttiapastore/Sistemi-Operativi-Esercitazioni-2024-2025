package esercizio4punto2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class esercizio4punto2 {
    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);

    static class A extends Thread{

        public void run(){
            try{
                semA.acquire();
                System.out.println("A");
                semB.release();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    static class B extends Thread{

        public void run(){
            try{
                semB.acquire();
                System.out.println("B");
                semA.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        while(true) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toSeconds(1));
                System.out.println();
            }catch (Exception e){
                e.printStackTrace();
            }
            new A().start();
            new B().start();

        }

    }


}
