package cinquefilosofi;

import java.util.concurrent.Semaphore;

public class TavoloSem extends Tavolo{

    //inizializzo i semafori e dopo li imposto tutti ad un permesso
    Semaphore bacchetta[] = new Semaphore[5];


    public  TavoloSem(){
        for(int i = 0 ; i < bacchetta.length ; i++){
            bacchetta[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        bacchetta[i].acquire();
        bacchetta[(i+1) % 5].acquire();
    }

    @Override
    public void rilasciaBacchette(int i) {
        bacchetta[i].release();
        bacchetta[(i+1) % 5].release();

    }

    public static void main(String[] args) throws InterruptedException {
        TavoloSem tavolo = new TavoloSem();
        tavolo.test();
    }
}
