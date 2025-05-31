package tracciacallcenter;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenterLC extends CallCenter{

    protected final int TMIN_SOLUZIONE = 1, TMAX_SOLUZIONE = 10;
    protected final int TMIN_APPLICAZIONE = 2 , TMAX_APPLICAZIONE = 6;
    protected final int CAFFEMAX = 15;

    private Lock l = new ReentrantLock();
    private Random rand = new Random();

    private Condition filaRichieste = l.newCondition();
    private Condition fornisci = l.newCondition();
    private Condition termina = l.newCondition();
    private Condition prossimo = l.newCondition();

    private int countOperatoreDisponibile = N;
    private int[] countPausaCaffe = new int[N];

    private boolean possoRichiedere = false;
    private boolean possoFornire = false;
    private boolean possoTerminare = false;
    private boolean possoChiamareProssimo = false;





    @Override
    public void richiediAssistenza() throws InterruptedException {
        l.lock();
        try{
            while (! possoRichiedere){
                filaRichieste.await();
            }
            System.out.println("Il cliente " + Thread.currentThread().getName() + " sta parlando con un operatore");
            fornisci.signal();
            possoFornire = true;
        }finally {
            l.unlock();
        }


    }

    @Override
    public void fornisciAssistenza() throws InterruptedException {
        l.lock();
        try{
            possoRichiedere = true;
            while(! possoFornire){
                fornisci.await();
            }
            possoFornire = false;
            filaRichieste.signal();
            countOperatoreDisponibile--;
            countPausaCaffe[((Operatore) Thread.currentThread()).getID()]++;
            System.out.println("L'operatore " + Thread.currentThread().getName() + " ha preso in carico la richiesta e sta fornendo soluzione");
            TimeUnit.SECONDS.sleep(rand.nextInt((TMAX_SOLUZIONE - TMIN_SOLUZIONE + 1) + TMIN_SOLUZIONE));
            System.out.println("Il cliente dell'operatore " + Thread.currentThread().getName() + " sta applicando la soluzione");
            TimeUnit.SECONDS.sleep(rand.nextInt((TMAX_APPLICAZIONE - TMIN_APPLICAZIONE + 1) + TMIN_APPLICAZIONE));
            possoTerminare = true;
            termina.signal();

        }finally {
            l.unlock();
        }

    }

    @Override
    public void terminaChiamata() throws InterruptedException {
        l.lock();
        try{
            while (! possoTerminare){
                termina.await();
            }
            possoTerminare = false;
            System.out.println("Il cliente " + Thread.currentThread().getName() + " ha finito di ricevere assistenza e ora chiude la chiamata");
            possoChiamareProssimo = true;
            prossimo.signal();
        }finally {
            l.unlock();
        }

    }

    @Override
    public void prossimoCliente() throws InterruptedException {
        l.lock();
        try{
            while(! possoChiamareProssimo){
                prossimo.await();
            }
            possoChiamareProssimo = false;
            if(countPausaCaffe[((Operatore) Thread.currentThread()).getID()] == CAFFEMAX) {
                System.out.println("L'operatore ha servito 15 clienti e va a prendersi un caffe");
                countPausaCaffe[((Operatore) Thread.currentThread()).getID()] = 0;
                TimeUnit.SECONDS.sleep(5);
            }
            countOperatoreDisponibile++;
            System.out.println(Thread.currentThread().getName() + ": prossimo cliente!");
            filaRichieste.signal();
            possoRichiedere = true;
        }finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        CallCenter c = new CallCenterLC();
        c.test();
    }

}
