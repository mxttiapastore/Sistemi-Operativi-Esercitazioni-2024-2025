package tracciapista;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PistaLC extends Pista{


    private Lock l = new ReentrantLock();

    private int countSmall = 0;
    private int countLarge = 0;
    private int countDentro = 0;

    private Condition noleggiaSmall = l.newCondition();
    private Condition noleggiaLarge = l.newCondition();
    private Condition entra = l.newCondition();

    protected final int GIRI_MIN = 3, GIRI_MAX = 10;

    private Random rand = new Random();

    @Override
    public void noleggia() throws InterruptedException {
        l.lock();
        int eta = ((Pilota) Thread.currentThread()).getEta();
        try{
            if(eta == MINORENNE){
                while(countSmall == S){
                    noleggiaSmall.await();
                }
                countSmall++;
                System.out.println("Il pilota " + Thread.currentThread().getName() + " ha noleggiato un gokart small");
            }else{
                while(countLarge == L){
                    noleggiaLarge.await();
                }
                countLarge++;
                System.out.println("Il pilota " + Thread.currentThread().getName() + " ha noleggiato un gokart large");
            }
        }finally {
            l.unlock();
        }
    }

    @Override
    public int entraInPista() throws InterruptedException {
        l.lock();
        try{
            int giri = rand.nextInt((GIRI_MAX -GIRI_MIN +1)+ GIRI_MIN);
            while(countDentro == CAPIENZAMAX){
                entra.await();
            }
            countDentro++;
            System.out.println("Il pilota " + Thread.currentThread().getName() + " è entrato a fare " + giri + " giri");
            return giri;
        }finally {
            l.unlock();
        }
    }

    @Override
    public void lasciaPista() throws InterruptedException {
        l.lock();
        try{
            System.out.println("Il pilota " + Thread.currentThread().getName() + " ha lasciato la pista");
            countDentro--;
            entra.signal();
        }finally {
            l.unlock();
        }

    }

    @Override
    public void riconsegna() throws InterruptedException {
        l.lock();
        try{
            int eta = ((Pilota) Thread.currentThread()).getEta();
            System.out.println("Il pilota " + Thread.currentThread().getName() + " sta riconsegnando un gokart " + (eta == MINORENNE ? "small" : "large"));
            switch (eta){
                case MINORENNE : {
                    noleggiaSmall.signal();
                    countSmall--;
                }
                case MAGGIORENNE : {
                    noleggiaLarge.signal();
                    countLarge--;
                }
            }
        }finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Pista p = new PistaLC();
        p.test();
    }
}
