package tracciapista;

public abstract class Pista {

    protected final int NSMALL = 5, NLARGE = 15, S = 4, L = 8, CAPIENZAMAX = 6;
    protected final int MINORENNE = 0, MAGGIORENNE = 1;


    public abstract void noleggia() throws InterruptedException;

    public abstract int entraInPista() throws InterruptedException;

    public abstract void lasciaPista() throws InterruptedException;

    public abstract void riconsegna() throws InterruptedException;

    public void test(){
        for(int i = 0; i < NSMALL ; i++){
            new Pilota(this, MINORENNE).start();
        }
        for(int i = 0 ; i < NLARGE ; i++){
            new Pilota(this, MAGGIORENNE).start();
        }
    }

}
