package tracciagalleriadegliuffizi;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Guida extends Thread{

    private Galleria galleria;
    private int lingua;

    private Random rand = new Random();

    public Guida(Galleria g , int l){
        galleria = g;
        lingua = l;
    }

    public void run(){
        try{
            while (true) {
                galleria.attendiVisitatori(lingua);
                galleria.terminaVisita(lingua);
            }
        }catch(InterruptedException e){}
    }

}
