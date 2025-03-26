package lettoriscrittori;

import java.util.concurrent.Semaphore;

public class MemoriaCondivisaSem extends MemoriaCondivisa{

    private int numLettori = 0;

    private Semaphore lettura = new Semaphore(1);
    private Semaphore lock = new Semaphore(1);

    public void inizioScrittura() throws InterruptedException{
        lock.acquire();
    }
    public void fineScrittura(){
        lock.release();
    }

    public void inizioLettura() throws InterruptedException {

        lettura.acquire();
        if(numLettori == 0) lock.acquire();
        numLettori ++;
        lettura.release();

    }

    public void fineLettura() throws InterruptedException{
        lettura.acquire();
        numLettori--;
        if (numLettori == 0) lock.release();
        lettura.release();
    }




}
