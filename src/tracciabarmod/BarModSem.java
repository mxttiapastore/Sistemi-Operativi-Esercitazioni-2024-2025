package tracciabarmod;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BarModSem extends BarMod{

    public Semaphore mutex = new Semaphore(1);
    public Semaphore bancone = new Semaphore(POSTI_BANCONE);
    public Semaphore cassa = new Semaphore(POSTO_CASSA);
    public int[] numeroPersoneInFila = new int[NUMERO_FILE];
    public int filaMaxBancone = 0;
    public int numeroPersoneCaffe = 0;



    public void inizia(int i) throws InterruptedException {
        mutex.acquire();
        numeroPersoneInFila[i]++;
        mutex.release();
        if(i == 0){
            bancone.acquire();
        }else{
            cassa.acquire();
        }
    }


    public void finisci(int i) throws InterruptedException {
        mutex.acquire();
        numeroPersoneInFila[i]--;
        mutex.release();

        if(i == 0){
            bancone.release();
        }else{
            cassa.release();
        }

    }


    public int scegli() {
        return new Random().nextInt(0, 2);
    }

    public static void main(String[] args) {

        BarMod bar = new BarModSem();
        bar.test();
    }


}
