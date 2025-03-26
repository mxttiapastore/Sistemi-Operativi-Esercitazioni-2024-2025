package lettoriscrittori;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Lettore extends Thread {

    private final static int MIN_TEMPO_LETTURA = 1;
    private final static int MAX_TEMPO_LETTURA = 4;
    private final static int MIN_TEMPO_ALTRO = 6;
    private final static int MAX_TEMPO_ALTRO = 10;

    private Random random = new Random();


    private MemoriaCondivisa memoria;

    public Lettore (MemoriaCondivisa mem){
        memoria = mem;
    }

    public void run(){

        try{
            while(true){
                memoria.inizioLettura();
                leggi();
                memoria.fineLettura();
                faiAltro();
            }

        }catch(InterruptedException e){}
    }

    private void leggi() throws InterruptedException{
        attendi(MIN_TEMPO_LETTURA , MAX_TEMPO_LETTURA);
    }

    private void faiAltro() throws InterruptedException{
        attendi(MIN_TEMPO_ALTRO , MAX_TEMPO_ALTRO);
    }

    private void attendi(int min, int max) throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt((max - min + 1) + min));
    }
}
