package tracciacasadicura;

import java.util.concurrent.TimeUnit;

public abstract class CasaDiCura {

    protected final int CAPIENZA_SALA_PICCOLA = 3;





    public abstract void chiamaEIniziaOperazione() throws InterruptedException;

    public abstract void fineOperazione() throws InterruptedException;

    public abstract void pazienteEntra() throws InterruptedException;

    public abstract void pazienteEsci() throws InterruptedException;


    public void test() throws InterruptedException {

        new Medico(this).start();

        while(true){
            new Paziente(this).start();
            TimeUnit.SECONDS.sleep(10);
        }

    }


}
