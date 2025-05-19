package tracciaufficiopostale;

public abstract class UfficioPostale {

    protected final int NUMERO_CLIENTI = 200;


    public abstract boolean ritiraTicket(String operazione) throws InterruptedException;

    public abstract void attendiSportello(String operazione) throws InterruptedException;

    public abstract void prossimoCliente() throws InterruptedException;

    public abstract void eseguiOperazione() throws InterruptedException;

    public void test(){
        Impiegato i = new Impiegato(this);
        i.setDaemon(true);
        for(int p = 0; p < NUMERO_CLIENTI ; p++){
            new Cliente(this).start();
        }
        i.start();




    }
}
