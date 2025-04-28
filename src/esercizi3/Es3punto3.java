package esercizi3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Es3punto3 {

    private static final int N = 10;
    private static Semaphore[] semaphores = new Semaphore[N];

    public static void main(String[] args) {
        for (int i = 0 ; i < N ; i++)
            semaphores[i] = new Semaphore(0);
        for(int i = 0 ; i < N ; i++){
            Task t = new Task(i);
            t.start();
        }
    }

    static class Task extends Thread{

        private int id;
        public Task(int id){ this.id = id;}

        public void run(){
            try{
                if(id < N - 1){
                    semaphores[id + 1].acquire();
                    System.out.println("state: " +  this.getState());
                }
                System.out.println("Esecuzione task: " + id);
                TimeUnit.SECONDS.sleep(1);
                semaphores[id].release();
            }catch (InterruptedException e){}
        }
    }
}
