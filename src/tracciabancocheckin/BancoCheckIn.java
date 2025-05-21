package tracciabancocheckin;

public abstract class BancoCheckIn {
    //per il test
    protected final int NUMERO_PASSEGGERI = 50;


    public abstract void deponeBagagli(int n) throws InterruptedException;

    public abstract void pesaERegistra() throws InterruptedException;

    public abstract void riceviCartaImbarco() throws InterruptedException;

    public abstract void prossimoPasseggero() throws InterruptedException;

    public void test(){
        new Addetto(this).start();
        for(int i = 0 ; i < NUMERO_PASSEGGERI ; i++){
            new Passeggero(this).start();
        }
    }

}
