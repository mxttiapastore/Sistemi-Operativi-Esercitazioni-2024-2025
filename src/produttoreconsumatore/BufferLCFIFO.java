package produttoreconsumatore;

import com.sun.source.tree.Tree;

import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLCFIFO extends  Buffer{

    private int numElementi = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition bufferNonPieno = lock.newCondition();
    private final Condition bufferPieno = lock.newCondition();

    //treeset cosi tengo conto degli id ordinati
    private final TreeSet<Long> produttoriInAttesa = new TreeSet<>();
    private final TreeSet<Long> consumatoriInAttesa = new TreeSet<>();

    public BufferLCFIFO(int dimensione){
        super(dimensione);
    }

    //Metodo monitor per inserire un elemento nel buffer
    public void put(int i) throws InterruptedException{
        lock.lock();
        try{
            long id = Thread.currentThread().getId();
            boolean aggiunto = false;

            /*
            il thread produttore deve attendere se:
            -il buffer è pieno
            -oppure se esiste un prodtuttore in attesa con id inferiore (prioritario)
             */
            while(numElementi == buffer.length ||
            !produttoriInAttesa.isEmpty() && produttoriInAttesa.first() < id){
                if(! aggiunto){
                    produttoriInAttesa.add(id);
                    aggiunto = true;
                }
                bufferNonPieno.await();
            }
            if(aggiunto){ produttoriInAttesa.remove(id); }

            buffer[in] = i;
            in = (in +1) % buffer.length;
            numElementi++;

            bufferNonPieno.signalAll();
            bufferPieno.signalAll();

        } finally {
            lock.unlock();
        }
    }

    @Override
    public int get() throws InterruptedException {
        int elemento;
        lock.lock();
        try{
            long id = Thread.currentThread().getId();
            boolean aggiunto = false;

            /*
            il thread consumatore attende se:
            -il buffer è vuoto
            -oppure se esiste un consumatore in attesa con id inferiore
             */
            while(numElementi == 0 ||
                    !consumatoriInAttesa.isEmpty() && consumatoriInAttesa.first() < id){
                if(!aggiunto){
                    consumatoriInAttesa.add(id);
                    aggiunto = true;
                }
                bufferNonPieno.await();
            }
            if(aggiunto){
                consumatoriInAttesa.remove(id);
            }

            elemento = buffer[out];
            out = (out + 1) % buffer.length;
            numElementi--;

            bufferNonPieno.signalAll();
            bufferPieno.signalAll();
        }finally{
            lock.unlock();
        }
        return elemento;
    }

}
