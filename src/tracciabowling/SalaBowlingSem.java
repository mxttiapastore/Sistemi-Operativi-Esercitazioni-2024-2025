package tracciabowling;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SalaBowlingSem extends SalaBowling{

    protected final int K = 4;
    protected final int TIRI_A_DISPOSIZIONE = 10;


    private TreeMap<String , Integer> informazioni = new TreeMap<>();


    private Semaphore mutex = new Semaphore(1);
    private Semaphore preparazione = new Semaphore(0);
    private Semaphore mioTurno = new Semaphore(0);
    private Semaphore consegna = new Semaphore(0);
    private Semaphore filaConsegna = new Semaphore(1);





    @Override
    public String fornisciInformazioni() throws InterruptedException {
        mutex.acquire();
        informazioni.put(Thread.currentThread().getName() , 0);
        System.out.println("Informazioni sul giocatore " + Thread.currentThread().getName() + " aggiunte.");
        preparazione.release();
        mutex.release();
        return Thread.currentThread().getName();
    }

    @Override
    public void preparaPartita() throws InterruptedException {
        preparazione.acquire(K);
        System.out.println("L'operatore ha raccolto le informazioni e sta consegnando le scarpe...");
        mutex.acquire();
        for(String giocatore : informazioni.keySet()){
            informazioni.put(giocatore , TIRI_A_DISPOSIZIONE);
            System.out.println("Giocatore " + giocatore +": scarpe consegnate");
        }
        mutex.release();
        System.out.println("Preparazione pista in corso... (10 sec)");
        TimeUnit.SECONDS.sleep(10);
        mioTurno.release();
    }

    @Override
    public void gioca() throws InterruptedException {
        mioTurno.acquire();
        String nome = Thread.currentThread().getName();
        if(informazioni.get(nome) == 0){
            System.out.println("Tiri finiti per " + nome);
            filaConsegna.acquire();
            consegna.release();
            System.out.println(nome +  ": scarpe consegnate.");

        }else{
            mutex.acquire();
            informazioni.put(nome , informazioni.get(nome)- 1);
            System.out.println(nome + " ha tirato");
        }
        TimeUnit.SECONDS.sleep(1); //per il test
        mioTurno.release();

    }

    @Override
    public void deposita() throws InterruptedException {
        consegna.acquire(K);
        System.out.println("Scarpe raccolte e depositate!");

    }

    public static void main(String[] args) {
        SalaBowling s = new SalaBowlingSem();
        s.test();
    }
}
