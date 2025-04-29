package esercizi3;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Es3punto7 {

    public static Lock l = new ReentrantLock();
    public static Condition c = l.newCondition();
    public static int turno;

    public static void main(String[] args) {

        int n = 10;
        turno = n;
        for(int i = -1 ; i <= n ; i++)
            new MyThread(i).start();
    }

    static class MyThread extends Thread{
        private int myId;

        public MyThread(int id){
            this.myId = id;
        }

        public void run(){
            l.lock();
            try{
                while(myId != turno)
                    c.await();
                System.out.printf("Thread %d %s\n" , myId, getState());
                TimeUnit.SECONDS.sleep(2);
                turno--;
                c.signalAll();
            }catch (InterruptedException e){e.printStackTrace();
            } finally{l.unlock();}
        }
    }
}
