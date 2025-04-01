package produttoreconsumatore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLC extends Buffer{

    protected int numElementi = 0;

    protected Lock l = new ReentrantLock();
    protected Condition bufferPieno = l.newCondition();
    protected Condition bufferVuoto = l.newCondition();

    public BufferLC(int dimensione){
        super(dimensione);
    }

    public void put(int i) throws InterruptedException{
        l.lock();
        try{
            while (numElementi == buffer.length){
                bufferPieno.await();
            }
            buffer[in] = i;
            in = (in + 1) % buffer.length;
            numElementi++;
            bufferVuoto.signal();
        } finally {
            l.unlock();
        }
    }

    public int get() throws InterruptedException{
        int elemento;
        l.lock();
        try{
            while (numElementi == 0){
                bufferVuoto.await();
            }
            elemento = buffer[out];
            out = (out + 1) % buffer.length;
            numElementi--;
            bufferPieno.signal();
        }finally {
            l.unlock();
        }
        return elemento;
    }
}
