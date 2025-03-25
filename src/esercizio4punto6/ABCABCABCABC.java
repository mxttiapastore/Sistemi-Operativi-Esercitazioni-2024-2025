package esercizio4punto6;

import java.util.concurrent.Semaphore;

public class ABCABCABCABC {

    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore semC = new Semaphore(0);


    static class A extends Thread{
        public void run(){
            try{
                semA.acquire();
                System.out.print("A");
                semB.release();
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }
    static class B extends Thread {
        public void run() {
            try {
                semB.acquire();
                System.out.print("B");
                semC.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class C extends Thread {
        public void run(){
            try{
                semC.acquire();
                System.out.print("C");
                semA.release();
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    public static void main(String[] args) {
        try{
            while(true){
                new A().start();
                new B().start();
                new C().start();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){e.printStackTrace();}


    }

}
