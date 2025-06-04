package tracciasaltatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SaltoSem extends Salto{

    private Random rand = new Random();

    private Semaphore mutex = new Semaphore(1);
    private Semaphore mioTurno = new Semaphore(0);
    private Semaphore chiama = new Semaphore(1);

    private TreeMap<Integer , Saltatore> classifica_finale = new TreeMap<>();
    private ArrayList<Double> classifica_temporanea = new ArrayList<Double>();

    private int count = N;


    @Override
    public void inizio(Saltatore s) throws InterruptedException {
        mioTurno.acquire();
        System.out.println("Salto del saltatore n. " + s.getN_maglia() + " iniziato");
    }

    @Override
    public int arrivo(Saltatore s) throws InterruptedException {
        int tempo_salto = rand.nextInt((T_MAX - T_MIN + 1) + T_MIN);
        TimeUnit.SECONDS.sleep(tempo_salto);
        double altezza_salto = rand.nextDouble((HMAX - HMIN +1)+ HMIN);
        mutex.acquire();
        classifica_temporanea.add(altezza_salto);

        int pos = classifica_temporanea.indexOf(altezza_salto);
        classifica_finale.put(pos , s);
        mutex.release();
        System.out.println("Salto del n. "+s.getN_maglia()+" compiuto. ALTEZZA: "+altezza_salto+" POSIZIONE: "+pos);
        chiama.release();
        return pos;

    }

    @Override
    public boolean successivo() throws InterruptedException {
        chiama.acquire();
        mutex.acquire();
        count--;
        if(count != 0){
            System.out.println("ZUMPA!!");
            mioTurno.release();
            return true;
        }
        else{
            System.out.println("GARA FINITA: CLASSIFICA FINALE:");
            for(int i = classifica_finale.size() ; i > 0 ; i--){
                System.out.println("POSIZIONE: "+i+"  NUMERO MAGLIA: "+classifica_finale.get(i).getN_maglia());
            }
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Salto s = new SaltoSem();
        s.test();
    }
}
