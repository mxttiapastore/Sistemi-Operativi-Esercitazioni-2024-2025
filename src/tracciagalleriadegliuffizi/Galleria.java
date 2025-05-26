package tracciagalleriadegliuffizi;

import java.util.concurrent.TimeUnit;

public abstract class Galleria {

    public abstract void iniziaVisita(int lingua) throws InterruptedException;

    public abstract void esci(int lingua) throws InterruptedException;

    public abstract void attendiVisitatori(int lingua) throws InterruptedException;

    public abstract void terminaVisita(int lingua) throws InterruptedException;


    public void test() throws InterruptedException{
        for(int i = 0 ; i < 5 ; i++){
            new Guida(this , i).start();
        }
        for(int i = 0 ; i < 100 ; i++){
            new Visitatore(this).start();
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
