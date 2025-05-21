package tracciabancocheckin;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Passeggero extends Thread{

    protected final int BAGAGLI_MIN = 1, BAGAGLI_MAX = 3;
    private Random rand = new Random();

    private BancoCheckIn banco;

    public Passeggero(BancoCheckIn b){
        banco = b;
    }

    public void run(){
        try{

            int N = rand.nextInt(1 , (BAGAGLI_MAX - BAGAGLI_MIN +1) + BAGAGLI_MIN);
            System.out.println("IL passeggero numero "+ Thread.currentThread().getId() + " sta preparando " + N + " bagagli (" + 15*N + " sec)");
            TimeUnit.SECONDS.sleep(15 * N);
            banco.deponeBagagli(N);
            System.out.println("Il passeggero numero " +  Thread.currentThread().getId() + " ha deposto i suoi " + N + " bagagli");
            banco.riceviCartaImbarco();

        }catch (InterruptedException e){}
    }
}
