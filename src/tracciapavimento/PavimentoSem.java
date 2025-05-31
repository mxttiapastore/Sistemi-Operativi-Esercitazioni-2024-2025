package tracciapavimento;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PavimentoSem extends Pavimento{

    protected final int M = 8, N = 4;
    protected final int A = 0, B = 1;

    private String[][] pavimento = new String[M][N];

    private int iAttuale = -1, jAttuale = -1;

    private Random rand = new Random();

    private Semaphore mutex = new Semaphore(1);
    private Semaphore lavoroA = new Semaphore(1);
    private Semaphore lavoroB = new Semaphore(0);

    private Semaphore piastrelleTot = new Semaphore((M * N)*2);

    public PavimentoSem(){
        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; i < N ; i++){
                pavimento[i][j] = "VUOTO";
            }
        }
    }

    @Override
    public String inizia(int T) throws InterruptedException {

        if(T == A){
            lavoroA.acquire();
            mutex.acquire();
            if(jAttuale == pavimento[0].length - 1){
                iAttuale++;
                jAttuale = 0;
            }else{
                jAttuale++;
            }
            int lavoro = rand.nextInt(4, 7);
            System.out.println("Il piastrellista " + Thread.currentThread().getName() + " sta posando la colla sul blocco B_"+iAttuale+"_"+jAttuale);
            TimeUnit.SECONDS.sleep(lavoro);
            mutex.release();
            lavoroB.release();
            return "B_"+iAttuale+"_"+jAttuale;
        }else{
            lavoroB.acquire();
            int lavoro = rand.nextInt(2, 4);
            System.out.println("Il piastrellista " + Thread.currentThread().getName() + " sta posando la colla sul blocco B_"+iAttuale+"_"+jAttuale);
            TimeUnit.SECONDS.sleep(lavoro);
            lavoroA.release();
            return "B_"+iAttuale+"_"+jAttuale;
        }

    }

    @Override
    public void finisci(int T, String B) throws InterruptedException {

    }
}
