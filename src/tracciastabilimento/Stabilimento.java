package tracciastabilimento;

public abstract class Stabilimento {

    protected final int N = 10; //numero bagnanti

    protected final int LETTINO = 0, OMBRELLONE = 1;

    protected final int TEMPO_LETTINO = 1, TEMPO_OMBRELLONE = 2;

    protected final int COSTO_LETTINO = 10, COSTO_OMBRELLONE = 15;





    public abstract void scegliAccesso() throws InterruptedException;

    public abstract void preparaPostazioni() throws InterruptedException;

    public abstract void paga() throws InterruptedException;

    public abstract void chiusura() throws InterruptedException;


    public void test(){

        for(int i = 0 ; i < N ; i++){
            new Bagnante(this).start();
        }
        new Gestore(this).start();

    }
}
