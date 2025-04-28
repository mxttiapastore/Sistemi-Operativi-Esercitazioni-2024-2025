package esercizi3;

import java.util.concurrent.TimeUnit;

public class Es3punto6 {

    public static void main(String[] args) {
        int n = 10;
        MyThread[] threads = new MyThread[n];
        for(int i = 0 ; i < n ; i++)
            threads[i] = new MyThread(i , threads);
        for(int i = 0 ; i < n ; i++)
            threads[i].start();
    }

    static class MyThread extends Thread{
        private int id;
        private MyThread[] threads;

        public MyThread(int id, MyThread[] threads){
            this.id = id;
            this.threads = threads;
        }

        public void run(){
            try{
                int p = id - 1;
                if (p >= 0)
                    threads[p].join();
                TimeUnit.SECONDS.sleep(id);
                if(p >= 0)
                    System.out.printf("Thread-%d Thread-%d %s\n", id , p, threads[p].getState());
                else
                    System.out.printf("Thread-%d\n", id);
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
