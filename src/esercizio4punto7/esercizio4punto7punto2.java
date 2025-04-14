package esercizio4punto7;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class esercizio4punto7punto2 {

    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore semC = new Semaphore(0);

    private final static int RIPETIZIONE = 6;
    private static Random scambio = new Random();

    private static AtomicInteger attesaB = new AtomicInteger();
    private static AtomicInteger attesaC = new AtomicInteger();

    static class A extends Thread{

        public void run(){
            try{
                semA.acquire();
                System.out.print("A");

                if(attesaB.get() < attesaC.get()) {
                    semC.release();
                }
                else semB.release();

                if(scambio.nextBoolean()) semB.release();
                else semC.release();

            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    static class B extends Thread{

        public void run(){
            attesaB.incrementAndGet();
            try{
                semB.acquire();
                for(int i = 0 ; i < RIPETIZIONE ; i++){
                    System.out.print("B");
                }
                semA.release();
            }catch(InterruptedException e){e.printStackTrace();}
            finally {
                attesaB.decrementAndGet();
            }
        }
    }

    static class C extends Thread{

        public void run(){

            attesaC.incrementAndGet();
            try{
                semC.acquire();
                for(int i = 0; i < RIPETIZIONE ; i++){
                    System.out.print("C");
                }
                semA.release();
            }catch (InterruptedException e){e.printStackTrace();}
            finally {
                attesaC.decrementAndGet();
            }
        }
    }
    public static void main(String[] args) {
        try{
            while (true){
                new A().start();
                new B().start();
                new C().start();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){e.printStackTrace();}
    }


}
