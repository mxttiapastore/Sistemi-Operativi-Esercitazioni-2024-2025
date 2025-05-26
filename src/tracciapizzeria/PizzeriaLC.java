package tracciapizzeria;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PizzeriaLC extends Pizzeria{

    protected final int TEMPO_PREPARAZIONE = 10; //minuti

    private Lock l = new ReentrantLock();

    private int count = 0;

    private boolean puoiSederti = true;
    private boolean puoiCucinare = false;
    private boolean puoiMangiare = false;

    private Condition entrata = l.newCondition();
    private Condition cucina = l.newCondition();
    private Condition mangia = l.newCondition();




    @Override
    public void entra() throws InterruptedException {
        l.lock();
        try{
            while(! puoiSederti){
                entrata.await();
            }
            System.out.println("Il cliente " + Thread.currentThread().getId() + " si è seduto.");
            count++;
            if(count == 5){
                puoiSederti = false;
                puoiCucinare = true;
                cucina.signal();
            }

        }finally {
            l.unlock();
        }

    }

    @Override
    public void mangiaPizza() throws InterruptedException {
        l.lock();
        try{
            while(! puoiMangiare){
                mangia.await();
            }
            System.out.println("Il cliente " + Thread.currentThread().getId() + " sta mangiando la pizza...(10 sec)");
            TimeUnit.SECONDS.sleep(10); //per test
            System.out.println("Il cliente "+ Thread.currentThread().getId() + " ha finito di mangiare ed è andato via.");
            count--;
            entrata.signal();
            if(count == 0){
                puoiSederti = true;
            }
        }finally{
            l.unlock();
        }
    }

    @Override
    public void preparaPizza() throws InterruptedException {
        l.lock();
        try{
            while(! puoiCucinare){
                cucina.await();
            }
            puoiCucinare = false;
            System.out.println("Il pizzaiolo sta preparando la maxi pizza... (10 sec) ");
            TimeUnit.SECONDS.sleep(TEMPO_PREPARAZIONE);
        }finally{
            l.unlock();
        }

    }

    @Override
    public void serviPizza() throws InterruptedException {
        l.lock();
        try{
            System.out.println("La pizza è pronta.");
            puoiMangiare = true;
            mangia.signalAll();
        }finally {
            l.unlock();
        }

    }

    public static void main(String[] args) {
        Pizzeria p = new PizzeriaLC();
        p.test();
    }
}
