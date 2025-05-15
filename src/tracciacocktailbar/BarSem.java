package tracciacocktailbar;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarSem extends Bar{


    ArrayList<Integer> listaCocktailOrdinati = new ArrayList<>();
    private Semaphore mutex = new Semaphore(1);
    private Semaphore permettiPreparazione = new Semaphore(0);
    private Semaphore permettiChiusura = new Semaphore(0);


    @Override
    public void ordinaCocktail(int tipo) throws InterruptedException {
        mutex.acquire();
        listaCocktailOrdinati.add(tipo);
        System.out.println("Il cliente numero " + Thread.currentThread().getId() + " ha ordinato un cocktail di tipo " + (tipo == 0 ? "normale" : "speciale" ));
        permettiPreparazione.release();
        mutex.release();
    }

    @Override
    public void preparaCocktail() throws InterruptedException {
        permettiPreparazione.acquire(NUMERO_CLIENTI);
        mutex.acquire();
        for(int cocktail : listaCocktailOrdinati){
            System.out.println("Il barman sta preparando il cocktail numero: " + listaCocktailOrdinati.indexOf(cocktail) + 1 + "...");
            TimeUnit.SECONDS.sleep(cocktail == 0 ? 1 : 2);
            permettiChiusura.release();
        }
        mutex.release();

    }


    @Override
    public void beviEPaga(int tipo) throws InterruptedException {
        mutex.acquire();
        incasso += (tipo == 0 ? 6 : 8);
        mutex.release();
    }

    @Override
    public void chiudiBar() throws InterruptedException {

        permettiChiusura.acquire(NUMERO_CLIENTI);
        mutex.acquire();
        System.out.println("U bar è chiuso, incasso: " + incasso);

    }

    public static void main(String[] args) {
        Bar barr = new BarSem();
        barr.test();
    }
}
