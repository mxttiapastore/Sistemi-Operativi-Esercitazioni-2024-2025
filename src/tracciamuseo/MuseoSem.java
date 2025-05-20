package tracciamuseo;

import java.net.MulticastSocket;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MuseoSem extends MuseoC{

    protected final int MAX_PERSONE_SA = 40, MAX_PERSONE_SD = 5;

    private Semaphore salaArcheologica = new Semaphore(MAX_PERSONE_SA);
    private Semaphore salaDama = new Semaphore(MAX_PERSONE_SD);

    private Random rand = new Random();


    @Override
    public void visitaSA() throws InterruptedException {
        salaArcheologica.acquire();
        int attesaSA = rand.nextInt((tempoSAMax - tempoSAMin + 1)+tempoSAMin);
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " è entrato in SA e la sta visitando...");
        TimeUnit.SECONDS.sleep(attesaSA);
    }

    @Override
    public void terminaVisitaSA() throws InterruptedException {
        salaArcheologica.release();
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " ha finito di visitare SA e si è messo in fila per SD");
    }

    @Override
    public void visitaSD() throws InterruptedException {
        salaDama.acquire();
        int attesaSD = rand.nextInt((tempoSDMax - tempoSDMin + 1) + tempoSDMin);
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " ha è entrato in SD e la sta visitando...");
        TimeUnit.SECONDS.sleep(attesaSD);
    }

    @Override
    public void terminaVisitaSD() throws InterruptedException {
        salaDama.release();
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " è uscito dal museo.");

    }

    public static void main(String[] args) throws InterruptedException {
        MuseoC m = new MuseoSem();
        m.test();
    }
}
