package tracciagalleriadegliuffizi;

import java.util.Random;

public class Visitatore extends Thread{

    protected final int MIN_LINGUA = 0 , MAX_LINGUA = 4;

    private Galleria galleria;
    private int lingua;

    private Random rand = new Random();

    public Visitatore(Galleria g){
        galleria = g;
        lingua = rand.nextInt((MAX_LINGUA - MIN_LINGUA +1) + MIN_LINGUA);
    }

    public void run(){
        try{
            galleria.iniziaVisita(lingua);
            galleria.esci(lingua);
        }catch(InterruptedException e){}
    }


}
