package tracciafunivia;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio3 {

    static Semaphore semA = new Semaphore(2);
    static Semaphore semB = new Semaphore(0);

    static int count = 0;

    static class A extends Thread{

        public void run(){
            try{
                while(true){
                    semA.acquire();
                    System.out.print("A");
                    semB.release();
                }
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }

    static class B extends Thread{

        public void run(){
            try{
                while(true){
                    semB.acquire(2);
                    TimeUnit.SECONDS.sleep(3);
                    count++;
                    System.out.print("B(" + count + ")");
                    semA.release(2);
                }
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }

    public static void main(String[] args) {
        new A().start();
        new B().start();
    }
}
