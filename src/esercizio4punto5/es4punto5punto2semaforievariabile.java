package esercizio4punto5;

import java.util.concurrent.Semaphore;

public class es4punto5punto2semaforievariabile {

    private static int count = 0;

    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);


    static class A extends Thread{
        public void run(){
            try{
                semA.acquire();
                while(count < 2){
                count++;
                System.out.print("A");}
                semB.release();
            }catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    static class B extends Thread{
        public void run(){
            try{
                semB.acquire();
                System.out.print("B");
                semA.release();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    public static void main(String[] args) {
        try{
            while (true){
                new es4punto5punto1solosemafori.A().start();
                new es4punto5punto1solosemafori.B().start();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {e.printStackTrace();}
    }

}
