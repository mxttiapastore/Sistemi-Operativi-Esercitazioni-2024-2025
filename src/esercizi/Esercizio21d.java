package esercizi;

public class Esercizio21d {

    public static void main(String[] args) {
        stampa();
        MyThread t1 = new MyThread("T1");
        t1.start();
    }

    public static void stampa() {
        Thread t = Thread.currentThread();
        System.out.printf("%s %s\n", t.getName(), t.getState());
    }

}
class MyThread extends Thread {
    public MyThread(String name) {
        setName(name);
    }
    public void run() {
        System.out.printf("%s %s\n",getName(), getState());
    }
}
