package tracciacinema;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CinemaSem extends Cinema{

    protected final int DURATA = 120; //MINUTI

    private ArrayList<Integer> posti_assegnati = new ArrayList<>();

    private Semaphore mutex = new Semaphore(1);

    private Semaphore filaCassa = new Semaphore(0);

    private Semaphore proietta = new Semaphore(0);

    private Semaphore esci = new Semaphore(0);

    private Semaphore chiudi = new Semaphore(0);

    private Semaphore biglietto = new Semaphore(0);

    private Random rand = new Random();


    @Override
    public void acquistaBiglietto() throws InterruptedException {
        filaCassa.acquire();
        System.out.println("Il cliente " + Thread.currentThread().getName() +
                " si è messo in fila");
        biglietto.release();
        System.out.println("Il cliente "+ Thread.currentThread().getName() +
                " ha acquistato il biglietto");

    }

    @Override
    public boolean consegnaBiglietto() throws InterruptedException {
        filaCassa.release();
        int posto = rand.nextInt(B_MIN , B_MAX + 1);

        while(posti_assegnati.contains(posto)){
            posto = rand.nextInt(B_MIN , B_MAX + 1);
        }
        biglietto.acquire();
        mutex.acquire();
        posti_assegnati.add(posto);
        System.out.println("L'addetto ha staccato un biglietto con il posto "+ posto);

        mutex.release();
        if(posti_assegnati.size() == N){
            System.out.println("FILM INIZIATO");
            proietta.release(N);
            TimeUnit.SECONDS.sleep(30);
            esci.release(N);
            return true;
        }
        return false;
    }

    @Override
    public void vediFilm() throws InterruptedException {
        proietta.acquire();
        System.out.println("Il cliente " + Thread.currentThread().getName() +
                " sta guardando il film");
        esci.acquire();
        System.out.println("Il cliente " + Thread.currentThread().getName() +
                " ha lasciato il cinema");
        chiudi.release();

    }

    @Override
    public void chiudiCinema() throws InterruptedException {

        chiudi.acquire(N);
        System.out.println("Sono usciti tutti. CINEMA CHIUSO");

    }

    public static void main(String[] args) {
        Cinema c = new CinemaSem();
        c.test();
    }
}
