package esercizio4punto6;

import java.util.concurrent.Semaphore;

public class AABBAABB {

    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);

    private static int countA = 0;
    private static int countB = 0;

    static class A extends Thread{

        public void run(){
            try{
                semA.acquire();

                while(countA < 2) {

                    System.out.print("A");
                    countA++;
                }
                countB = 0;
                semB.release();

            }catch (InterruptedException e){ e.printStackTrace();}

        }

    }

    static class B extends Thread{

        public void run(){
            try{

                semB.acquire();

                while (countB < 2) {

                    System.out.print("B");
                    countB++;

                }
                countA = 0;
                semA.release();

            }catch(InterruptedException e){e.printStackTrace();}


        }
    }

    public static void main(String[] args) {

        try{
            while(true){

                new A().start();
                new B().start();
                Thread.sleep(1000);

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }




}
