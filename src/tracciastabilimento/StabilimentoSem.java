package tracciastabilimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StabilimentoSem extends Stabilimento {

    protected final int N = 10; //numero bagnanti

    private int count = 0;
    private int incasso = 0;
    private List<Integer> scelte = new ArrayList<>();

    private Semaphore permettiPreparazione = new Semaphore(0);

    private Semaphore mutex = new Semaphore(1);

    private Semaphore permettiPagamento = new Semaphore(0);

    private Semaphore permettiChiusura = new Semaphore(0);


    private Random rand = new Random();


    @Override
    public void scegliAccesso() throws InterruptedException {
        mutex.acquire();
        count++;
        if(count == N){
            int scelta = rand.nextInt(OMBRELLONE + 1);
            scelte.add(scelta);
            permettiPreparazione.release();
            mutex.release();
        }else{
            int scelta = rand.nextInt(OMBRELLONE + 1);
            scelte.add(scelta);
            System.out.println("Il bagnante numero " + Thread.currentThread());
            mutex.release();
        }

    }

    @Override
    public void preparaPostazioni() throws InterruptedException {
        permettiPreparazione.acquire();
        for(int scelta : scelte){
            System.out.println("Il gestore sta preparando un " + (scelta == LETTINO ? "lettino..." : "ombrellone..."));
            TimeUnit.SECONDS.sleep(scelta == LETTINO ? TEMPO_LETTINO : TEMPO_OMBRELLONE);
        }
        permettiPagamento.release();

    }

    @Override
    public void paga() throws InterruptedException {
        permettiPagamento.acquire();
        mutex.acquire();
        if(scelte.isEmpty()){
            permettiChiusura.release();
            mutex.release();
        }else{
            int scelta = scelte.getFirst();
            if(scelta == LETTINO) incasso += COSTO_LETTINO;
            else incasso += COSTO_OMBRELLONE;
            scelte.removeFirst();
            mutex.release();
        }
    }

    @Override
    public void chiusura() throws InterruptedException {
        permettiChiusura.acquire();
        System.out.println("Il gestore ha chiuso lo stabilimento, con un incasso di "+ incasso + " euro.");

    }
}
