package tracciamuseo;

import java.util.concurrent.TimeUnit;

public abstract class MuseoC {

    protected final int tempoSAMin = 20, tempoSAMax = 40;
    protected final int tempoSDMin = 5, tempoSDMax = 8;



    public abstract void visitaSA() throws InterruptedException;

    public abstract void terminaVisitaSA() throws InterruptedException;

    public abstract void visitaSD() throws InterruptedException;

    public abstract void terminaVisitaSD() throws InterruptedException;

    public void test() throws InterruptedException{
        for(int i  = 0 ; i < 100 ; i++){
            TimeUnit.SECONDS.sleep(1);
            new Visitatore(this).start();

        }

    }

}
