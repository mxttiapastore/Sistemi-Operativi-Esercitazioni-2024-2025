package tracciamuseo;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MuseoSemVARIANT extends MuseoC{

    //adesso la sala della dama è visitabile solo da gruppi di 5 PERSONE PER VOLTA
    //solo quando tutti i visitatori lasciano la sala della dama potranno entrarne altri 5

    protected final int MAX_PERSONE_SA = 40, MAX_PERSONE_SD = 5;
    private final int NUM_CONSENTITO_GRUPPI_SD = 5;

    private int count = 0;

    private int countSD = 0;
    private Semaphore consentiSD = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
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

        mutex.acquire();
        count++;
        if(count == NUM_CONSENTITO_GRUPPI_SD && countSD == 0 ){
            consentiSD.release(NUM_CONSENTITO_GRUPPI_SD);
            count = 0;
            System.out.println("Gruppo da 5 persone creato");
        }
        mutex.release();
        consentiSD.acquire();
    }

    @Override
    public void visitaSD() throws InterruptedException {
        mutex.acquire();
        consentiSD.acquire();
        countSD++;
        int attesaSD = rand.nextInt((tempoSDMax - tempoSDMin + 1) + tempoSDMin);
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " ha è entrato in SD e la sta visitando...");
        TimeUnit.SECONDS.sleep(attesaSD);
        mutex.release();
    }

    @Override
    public void terminaVisitaSD() throws InterruptedException {
        mutex.acquire();
        System.out.println("Il visitatore numero " + Thread.currentThread().getId() + " è uscito dal museo.");
        countSD--;
        mutex.release();
    }

    public static void main(String[] args) throws InterruptedException {
        MuseoC m = new MuseoSemVARIANT();
        m.test();
    }
}
