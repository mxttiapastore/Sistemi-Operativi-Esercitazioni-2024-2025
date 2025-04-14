package cinquefilosofi;

import java.util.concurrent.Semaphore;

public class TavoloSem extends Tavolo{

    //inizializzo i semafori e dopo li imposto tutti ad un permesso



    public  TavoloSem(){
        for(int i = 0; i < bacchette.length ; i++){
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        bacchette[i].acquire();
        bacchette[(i+1) % 5].acquire();
    }

    @Override
    public void rilasciaBacchette(int i) {
        bacchette[i].release();
        bacchette[(i+1) % 5].release();

    }

    public static void main(String[] args) throws InterruptedException {
        TavoloSem tavolo = new TavoloSem();
        tavolo.test();
    }
}
