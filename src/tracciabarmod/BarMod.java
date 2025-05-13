package tracciabarmod;

public abstract class BarMod {

    protected final int NUMERO_FILE = 2;
    protected final int POSTI_BANCONE = 4;
    protected final int POSTO_CASSA = 1;


    public abstract void inizia(int i) throws InterruptedException;


    public abstract void finisci(int i) throws InterruptedException;

    public abstract int scegli();

    public void test(){

        for(int i = 0 ; i < 100 ; i++){
            new Cliente(this).start();
        }
    }





}
