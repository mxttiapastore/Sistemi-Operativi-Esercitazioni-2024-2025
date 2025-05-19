package tracciacaselloSEMPLICE;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public abstract class Casello {

    protected final int NUMERO_PORTE_CASELLO = 10;
    protected final int TARIFFA_CHILOMETRICA = 2;
    protected int incasso = 0;
    protected final int NUMERO_VEICOLI = 30;

    public abstract void guida(int x) throws InterruptedException;

    public abstract void scegliEPaga(int x) throws InterruptedException;



    public void test(){
        try {
            for (int i = 0; i < NUMERO_VEICOLI; i++) {
                new Veicolo(this).start();
                TimeUnit.SECONDS.sleep(2);
            }
        }catch (InterruptedException e){}

    }


}
