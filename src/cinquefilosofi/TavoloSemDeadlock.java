package cinquefilosofi;

import java.util.concurrent.Semaphore;

public class TavoloSemDeadlock extends Tavolo {

    Semaphore[] bacchette = new Semaphore[5];

    public TavoloSemDeadlock() {
        for (int i = 0; i < bacchette.length; i++) {
            bacchette[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiBacchette(int i) throws InterruptedException {
        int first, second;
        // Per i filosofi da 0 a 3: prendi prima la bacchette i (sinistra) e poi quella (i+1)%5 (destra)
        // Per il filosofo 4: inverte l’ordine per rompere il ciclo di attesa
        if (i == 4) {
            first = (i + 1) % 5;
            second = i;
        } else {
            first = i;
            second = (i + 1) % 5;
        }
        bacchette[first].acquire();
        bacchette[second].acquire();
    }

    @Override
    public void rilasciaBacchette(int i) {
        int first, second;
        if (i == 4) {
            first = (i + 1) % 5;
            second = i;
        } else {
            first = i;
            second = (i + 1) % 5;
        }
        bacchette[first].release();
        bacchette[second].release();
    }

    public static void main(String[] args) throws InterruptedException {
        TavoloSemDeadlock tavolo = new TavoloSemDeadlock();
        tavolo.test();
    }
}
