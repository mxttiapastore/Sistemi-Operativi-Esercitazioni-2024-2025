package esercizio4punto8;

import java.util.concurrent.Semaphore;

public class esercizio4punto8 {

    private static int num_thread = 100;
    private static Semaphore[] semafori = new Semaphore[num_thread];

    public static void main(String[] args) {
        for(int i = 0 ; i < num_thread ; i++){
            semafori[i] = new Semaphore(0);
        }
        semafori[0].release();
        try {


            for (int i = 0; i < num_thread ; i++) {
                 int idThread = i;
                Thread t = new Thread(() -> {

                    try {
                        semafori[idThread].acquire();
                        System.out.println("Thread numero " + idThread + " in esecuzione");
                        if (idThread +1 != num_thread) semafori[idThread + 1].release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                t.start();
                Thread.sleep(1000);
                System.out.println("Thread numero " + idThread + " terminato");
            }


        }catch (InterruptedException e){e.printStackTrace();}
    }


}
