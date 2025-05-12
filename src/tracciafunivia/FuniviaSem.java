package tracciafunivia;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FuniviaSem extends Funivia {

    private int numViaggio = -1;
    private int postiOccupati = 0;

    private final int NUM_TURISTI_A_PIEDI = 6;
    private final int NUM_TURISTI_IN_BICI = 3;

    private Semaphore permettiEntrataInBici = new Semaphore(0);

    private Semaphore permettiEntrataAPiedi = new Semaphore(0);

    private Semaphore mutex = new Semaphore(1);

    private Semaphore permettiPartenza = new Semaphore(0);

    private Semaphore permettiUscitaAPiedi = new Semaphore(0);

    private Semaphore permettiUscitaInBici = new Semaphore(0);

    private Semaphore permettiStampa = new Semaphore(0);

    private Semaphore permettiFine = new Semaphore(0);

    private ArrayList<Long> IDTuristi = new ArrayList<>();

    @Override
    public void pilotaStart() throws InterruptedException {
        numViaggio++;

        if(numViaggio % 2 == 0){
            permettiEntrataAPiedi.release(NUM_TURISTI_A_PIEDI);
        }else{
            permettiEntrataInBici.release(NUM_TURISTI_IN_BICI);
        }
        permettiPartenza.acquire();
        permettiStampa.release();
    }


    @Override
    public void pilotaEnd() throws InterruptedException {
        permettiStampa.acquire();
        System.out.println("Viaggio numero: " + (numViaggio + 1));
        System.out.println("ID turisti presenti: ");

        for(int i = 0 ; i <  IDTuristi.size() ; i++){
            System.out.print(IDTuristi.get(i) + " ");
        }

        System.out.print("\n");

        if(numViaggio %2 == 0 ){
            permettiUscitaAPiedi.release(NUM_TURISTI_A_PIEDI);
        }else{
            permettiUscitaInBici.release(NUM_TURISTI_IN_BICI);
        }

        permettiFine.acquire();
        IDTuristi.clear();
    }


    @Override
    public void turistaSali(int tipo) throws InterruptedException {
        if(tipo == TURISTA_A_PIEDI){
            permettiEntrataAPiedi.acquire();
            mutex.acquire();
            postiOccupati++;
            IDTuristi.add(Thread.currentThread().getId());
            if(postiOccupati == NUMERO_POSTI_FUNIVIA){
                permettiPartenza.release();
            }
            mutex.release();
        }else{
            permettiEntrataInBici.acquire();
            mutex.acquire();
            postiOccupati+=2; //1 post turista + 1 posto bici
            IDTuristi.add(Thread.currentThread().getId());
            if(postiOccupati == NUMERO_POSTI_FUNIVIA){
                permettiPartenza.release();
            }
            mutex.release();
        }
    }

    @Override
    public void turistaScendi(int tipo) throws InterruptedException {
        if(tipo == TURISTA_A_PIEDI){
            permettiUscitaAPiedi.acquire();
            mutex.acquire();
            postiOccupati--;
            if(postiOccupati == 0){
                permettiFine.release();
            }
            mutex.release();
        }else{
            permettiUscitaInBici.acquire();
            mutex.acquire();
            postiOccupati -= 2;
            if(postiOccupati == 0){
                permettiFine.release();
            }
            mutex.release();
        }
    }


    public static void main(String[] args) {
        Funivia fun = new FuniviaSem();
        int numTuristiAPiedi = 18;
        int numTuristiInBici = 9;
        fun.test(18, 9);
    }


}
