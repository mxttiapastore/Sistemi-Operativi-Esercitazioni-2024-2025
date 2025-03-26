package produttoreconsumatore;

import java.util.concurrent.Semaphore;

public class BufferSem extends Buffer {

    private Semaphore ciSonoElementi = new Semaphore(0);
    private Semaphore ciSonoPostiVuoti = new Semaphore(1);
    private Semaphore mutex = new Semaphore(1);


    public BufferSem(int dimensione){
        super(dimensione);
        ciSonoPostiVuoti = new Semaphore(dimensione);
    }

    public void put(int i) throws InterruptedException{
        ciSonoPostiVuoti.acquire();
        mutex.acquire();
        buffer[in] = i;
        in = (in + 1) % buffer.length;
        mutex.release();
        ciSonoElementi.release();
        Thread.sleep(1000);
    }

    public int get() throws InterruptedException{
        ciSonoElementi.acquire();
        mutex.acquire();
        int i = buffer[out];
        out = (out + 1) % buffer.length;
        mutex.release();
        ciSonoPostiVuoti.release();
        Thread.sleep(1000);
        return i;

    }

    public static void main(String[] args) throws InterruptedException {

        int dimensione = 10;
        Buffer buffer = new BufferSem(dimensione);
        int numProduttori = 10;
        int numConsumatori = 10;
        buffer.test(numProduttori, numConsumatori);
    }
}
