package tracciacaselloSEMPLICE;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CaselloSem extends Casello{

    protected final int TEMPO_PER_CHILOMETRO = 1;
    protected final int TEMPO_PAGAMENTO_MIN = 3, TEMPO_PAGAMENTO_MAX = 6;

    private Random rand = new Random();
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] casello = new Semaphore[NUMERO_PORTE_CASELLO];

    {
        for(int i = 0 ; i < casello.length ; i++){
            casello[i] = new Semaphore(1);
        }
    }

    @Override
    public void guida(int x) throws InterruptedException {
        System.out.println("Il veicolo numero " + Thread.currentThread().getId() + " sta guidando per " + x + " chilometri...");
        TimeUnit.SECONDS.sleep(x * TEMPO_PER_CHILOMETRO);

    }

    @Override
    public void scegliEPaga(int x) throws InterruptedException {
        mutex.acquire();
        int scelta = rand.nextInt(casello.length);
        int tempo_pagamento = rand.nextInt((TEMPO_PAGAMENTO_MAX - TEMPO_PAGAMENTO_MIN + 1) + TEMPO_PAGAMENTO_MIN);
        casello[scelta].acquire();

        System.out.println("Il veicolo numero " + Thread.currentThread().getId() + " ha scelto la porta numero " + (scelta + 1));

        incasso += TARIFFA_CHILOMETRICA * x;

        System.out.println("Il veicolo numero " + Thread.currentThread().getId() + " sta pagando...");

        TimeUnit.SECONDS.sleep(tempo_pagamento);
        casello[scelta].release();

        System.out.println("Il veicolo numero " + Thread.currentThread().getId() + " se ne va dalla porta " + (scelta + 1));

        mutex.release();

    }

    public static void main(String[] args) {
        Casello c = new CaselloSem();
        c.test();
    }


}
