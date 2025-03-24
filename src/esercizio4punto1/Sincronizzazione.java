package esercizio4punto1;

import java.util.concurrent.Semaphore;

public class Sincronizzazione {

    private static Semaphore mutex = new Semaphore(0);
    static class P1 extends Thread{

        public void run(){
            try{
                mutex.acquire();
                System.out.println("A");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    static class P2 extends Thread{

        public void run(){
            System.out.println("B");
            mutex.release();
        }
    }
    public static void main(String[] args) {
        P1 p1 = new P1();
        P2 p2 = new P2();
        p1.start();
        p2.start();
    }

}
