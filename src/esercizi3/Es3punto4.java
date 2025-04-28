package esercizi3;

import java.util.concurrent.Semaphore;

public class Es3punto4 {

    private static Semaphore[] sems;

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        sems = new Semaphore[n];
        MyThread[] threads = new  MyThread[n];
        for(int i = 0 ; i < sems.length ; i++)
            sems[i] = new Semaphore(0);
        for(int i = 0 ; i < threads.length ; i++){
            threads[i] = new MyThread(i , n);
            threads[i].start();
        }
    }

    static class MyThread extends Thread{
        private int i , n;
        public MyThread(int i , int n){
            this.i = i;
            this.n = n;
        }

        public void run(){
            try{
                if (i > n/2){
                    sems[n - 1 - i].release();
                }
                else{
                    sems[i].acquire();
                    System.out.println("Thread " + i + " " +
                            Thread.currentThread().getState());
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
