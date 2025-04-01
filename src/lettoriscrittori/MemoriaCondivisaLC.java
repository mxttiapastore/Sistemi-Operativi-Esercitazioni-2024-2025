package lettoriscrittori;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MemoriaCondivisaLC extends  MemoriaCondivisa{


    private Lock l = new ReentrantLock();
    //possibilmente do alle condition un nome che indichi la condizione necessaria
    //per cui si aspetta
    private Condition possoScrivere = l.newCondition();
    private Condition possoLeggere = l.newCondition();

    private int numLettoriInLettura = 0;
    private boolean scrittoreInScrittura = false;





    @Override
    public void inizioScrittura() throws InterruptedException {
        l.lock();
        try{
            while(numLettoriInLettura > 0 || scrittoreInScrittura){
                possoScrivere.await();
            }
            scrittoreInScrittura = true;
        }finally {
            l.unlock();
        }

    }

    @Override
    public void fineScrittura() throws InterruptedException {
        l.lock();
        try{
            scrittoreInScrittura = false;
            possoLeggere.signalAll();
            possoScrivere.signal();
        } finally {
            l.unlock();
        }

    }

    @Override
    public void inizioLettura() throws InterruptedException {
        l.lock();
        try {
            while (scrittoreInScrittura){
                possoLeggere.await();
            }
            numLettoriInLettura++;
        } finally {
            l.unlock();
        }

    }

    @Override
    public void fineLettura() throws InterruptedException {
        l.lock();
        try{
            numLettoriInLettura--;
            if(numLettoriInLettura == 0){
                possoScrivere.signal();
            }
        }finally{
            l.unlock();
        }


    }
}
