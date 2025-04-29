package tracciaziendaagricola;

public abstract class AziendaAgricola {

    protected final int MAX_SACCHI = 20;
    protected int num_sacchi;
    protected int incasso;


    public AziendaAgricola(){
        num_sacchi = MAX_SACCHI;
        incasso = 0;
    }

    public abstract void caricaSacchetti() throws InterruptedException;

    public abstract void ritiraSacchetti(int num, Cliente cliente) throws InterruptedException;

    public abstract void attendiRicarica() throws InterruptedException;

    public void test(int num_clienti){
        Magazziniere m = new Magazziniere(this);
        m.setDaemon(true);
        m.start();
        for(int i = 1 ; i <= num_clienti ; i++){
            Cliente c = new Cliente(this , i);
            c.start();
        }
    }
}
