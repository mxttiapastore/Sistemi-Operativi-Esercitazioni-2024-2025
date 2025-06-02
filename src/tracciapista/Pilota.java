package tracciapista;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{

    private Pista pista;
    private int eta;

    private Random rand = new Random();

    public Pilota(Pista p , int e){
        pista = p;
        eta = e;
    }

    public void run(){

        try{
            pista.noleggia();
            int giri = pista.entraInPista();
            for(int i = 0 ; i < giri ; i++){
                TimeUnit.SECONDS.sleep(rand.nextInt(1,3));
            }
            System.out.println("Il pilota " + Thread.currentThread().getName() + " ha finito i suoi giri");
            pista.lasciaPista();
            pista.riconsegna();
        }catch (InterruptedException e){}
    }

    public int getEta(){
        return eta;
    }
}
