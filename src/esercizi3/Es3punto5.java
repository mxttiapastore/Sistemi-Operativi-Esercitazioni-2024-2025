package esercizi3;

public class Es3punto5 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread());
        t1.start();
        t1.join();
        System.out.println(t1.getState().equals(Thread.State.RUNNABLE));
        Thread t2 = new Thread(new MyThread());
        t2.start();
        System.out.println(t2.getState().equals(Thread.State.RUNNABLE));
    }

    static class MyThread implements Runnable{

        public void run(){
            System.out.println("SISOP");
        }
    }
}
