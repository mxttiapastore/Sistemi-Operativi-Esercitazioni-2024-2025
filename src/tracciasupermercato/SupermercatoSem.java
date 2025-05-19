package tracciasupermercato;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SupermercatoSem extends Supermercato{

    protected final int CASSA_LIBERA = 1, CASSA_OCCUPATA = 0;
    protected final int NUMERO_CASSIERI = 10;
    protected final int NUMERO_CLIENTI = 100;
    private Random rand = new Random();
    private Semaphore[] casse = new Semaphore[NUMERO_CASSIERI];


    {
        for(int i = 0 ; i < NUMERO_CASSIERI ; i++){
            casse[i] = new Semaphore(1);
        }
    }
    private Semaphore mutex = new Semaphore(0);



    @Override
    public int getIdCassa() throws InterruptedException {
        mutex.acquire();
        int id = rand.nextInt(NUMERO_CASSIERI);
        return id;
    }

    @Override
    public void consegnaProdotti(int id, int p) throws InterruptedException {
        casse[id].acquire();
        TimeUnit.SECONDS.sleep(p);
    }

    @Override
    public int segnalaCassaLibera(int id) throws InterruptedException {
        return 0;
    }

    @Override
    public void congedaCliente(int id) throws InterruptedException {

    }
}
