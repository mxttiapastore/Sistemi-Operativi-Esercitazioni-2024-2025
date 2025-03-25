package esercizio4punto7;

import java.util.concurrent.Semaphore;

public class esercizio4punto7 {


    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore semC = new Semaphore(0);

    private static int ripetizione = 1;
    private static boolean scambio = false;

    static class A extends Thread{
        public void run(){
            try{
                semA.acquire();
                System.out.print("A");
                ripetizione = 6;
                if(scambio) semC.release();
                else semB.release();
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }
    static class B extends Thread{
        public void run(){
            try{
                semB.acquire();
                for(int i = 0 ; i < ripetizione ; i++) {
                    System.out.print("B");
                }
                ripetizione = 1;
                scambio = true;
                semA.release();
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }
    static class C extends Thread{
        public void run(){
            try{
                semC.acquire();
                for(int i = 0; i < ripetizione ; i++){
                    System.out.print("C");
                }
                ripetizione = 1;
                scambio = false;
                semA.release();
            }catch (InterruptedException e){e.printStackTrace();}
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
