package tracciacaselloSEMPLICE;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread{

    protected final int CHILOMETRI_MIN = 50, CHILOMETRI_MAX = 100;
    private Random random = new Random();

    private Casello casello;

    public Veicolo(Casello c){
        casello = c;
    }


    public void run(){

        try{
            int x = random.nextInt((CHILOMETRI_MAX - CHILOMETRI_MIN + 1)+ CHILOMETRI_MIN);
            casello.guida(x);
            casello.scegliEPaga(x);
        }catch(InterruptedException e){}
    }
}
