package tracciagalleriadegliuffizi;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class GalleriaSem extends Galleria{

    protected final int CAPIENZA_MAX = 200;
    protected final int NUMERO_LINGUE = 5;
    protected final int RIPOSO_FINE = 30; // minuti
    protected final int ATTESA_GRUPPI = 20; //minuti

    private Semaphore mutex = new Semaphore(1);

    private Semaphore filaMuseo = new Semaphore(CAPIENZA_MAX);

    private Semaphore[] attendiFine = new Semaphore[NUMERO_LINGUE];
    private Semaphore[] codaLingue = new Semaphore[NUMERO_LINGUE];
    private boolean[] guidaOccupata = new boolean[NUMERO_LINGUE];
    private int[] countIngressi = new int[NUMERO_LINGUE];

    private Random rand = new Random();



    public GalleriaSem(){
        for(int i = 0 ; i < NUMERO_LINGUE ; i++){
            codaLingue[i] = new Semaphore(30);
            guidaOccupata[i] = false;
            countIngressi[i] = 0;
            attendiFine[i] = new Semaphore(0);
        }

    }

    @Override
    public void iniziaVisita(int lingua) throws InterruptedException {
        filaMuseo.acquire();
        while(true) {
            if( ! guidaOccupata[lingua]) {
                System.out.println("Il visitatore " + Thread.currentThread().getId() + " è entrato nella galleria");
                codaLingue[lingua].acquire();
                System.out.println("Il visitatore " + Thread.currentThread().getId() + " si è messo in fila per la lingua " + lingua);
                mutex.acquire();
                countIngressi[lingua]++;
                mutex.release();
                break;
            }
        }
    }

    @Override
    public void esci(int lingua) throws InterruptedException {
        attendiFine[lingua].acquire();
        System.out.println("Il visitatore " + Thread.currentThread().getId() + " è uscito dal museo");
        filaMuseo.release();



    }

    @Override
    public void attendiVisitatori(int lingua) throws InterruptedException {
        System.out.println("La guida per la lingua " + lingua + " sta aspettando nuovi visitatori...(20 secs)");
        TimeUnit.SECONDS.sleep(ATTESA_GRUPPI);
        mutex.acquire();
        guidaOccupata[lingua] = true;
        mutex.release();
        System.out.println("Visita per la lingua " + lingua + " iniziata. Partecipanti: " + countIngressi[lingua]);
        TimeUnit.SECONDS.sleep(rand.nextInt(2, 4));



    }

    @Override
    public void terminaVisita(int lingua) throws InterruptedException {
        System.out.println("Visita per la lingua " + lingua + " finita. Ora la guida si riposa (30 secs)");
        attendiFine[lingua].release(countIngressi[lingua]);
        mutex.acquire();
        countIngressi[lingua] = 0;
        mutex.release();
        TimeUnit.SECONDS.sleep(RIPOSO_FINE);
        guidaOccupata[lingua] = false;

    }

    public static void main(String[] args) throws InterruptedException {
        Galleria g = new GalleriaSem();
        g.test();
    }
}
