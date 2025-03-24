package esercizio4punto5;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class es4punto5punto1solosemafori {

    private static Semaphore semA = new Semaphore(2);
    private static Semaphore semB = new Semaphore(0);
    static class A extends Thread{
        public void run(){
            try{
                semA.acquire();
                System.out.print("A");
                semB.release();
            }catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    static class B extends Thread{
        public void run(){
            try{
                semB.acquire(2);
                System.out.print("B");
                semA.release(2);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    public static void main(String[] args) {
       try{
           while (true){
               new A().start();
               new B().start();
               Thread.sleep(1000);
           }
       }catch (InterruptedException e) {e.printStackTrace();}
    }







}
