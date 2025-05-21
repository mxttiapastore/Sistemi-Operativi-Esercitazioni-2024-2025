package tracciabancocheckin;

import javax.imageio.stream.ImageInputStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BancoCheckInSem extends BancoCheckIn{

    private int n_bagagli;

    protected final int TEMPO_PESO_PER_BAGAGLIO = 10;

    private Semaphore mutex = new Semaphore(1);

    private Semaphore permettiPeso = new Semaphore(0);

    private Semaphore permettiCartaImbarco = new Semaphore(0);

    private Semaphore prossimocliente = new Semaphore(0);



    @Override
    public void deponeBagagli(int n) throws InterruptedException {
        mutex.acquire();
        n_bagagli = n;
        mutex.release();
        permettiPeso.release();

    }

    @Override
    public void pesaERegistra() throws InterruptedException {
        permettiPeso.acquire();
        System.out.println("L'addetto sta pesando e registrando " + n_bagagli + " bagagli (" + TEMPO_PESO_PER_BAGAGLIO*n_bagagli + " sec)");
        TimeUnit.SECONDS.sleep(TEMPO_PESO_PER_BAGAGLIO * n_bagagli );
        permettiCartaImbarco.release();
    }

    @Override
    public void riceviCartaImbarco() throws InterruptedException {
        permettiCartaImbarco.acquire();
        System.out.println("Check-In del passeggero numero " + " completato");
        prossimocliente.release();

    }

    @Override
    public void prossimoPasseggero() throws InterruptedException {
        prossimocliente.acquire();
        System.out.println("Prossimo cliente!");

    }

    public static void main(String[] args) {
        BancoCheckIn b = new BancoCheckInSem();
        b.test();
    }
}
