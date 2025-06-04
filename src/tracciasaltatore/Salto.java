package tracciasaltatore;

import java.util.concurrent.TimeUnit;

public abstract class Salto {

    protected final int N = 50 , T_MIN = 15, T_MAX = 40, H = 1;
    protected final double HMIN = 4.5, HMAX = 6.5;

    public abstract void inizio(Saltatore s) throws InterruptedException;

    public abstract int arrivo(Saltatore s) throws InterruptedException;

    public abstract boolean successivo() throws InterruptedException;

    public void test() throws InterruptedException {
        new Giudice(this).start();
        for(int i = 0 ; i < N ; i++){
            new Saltatore(this, i).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
