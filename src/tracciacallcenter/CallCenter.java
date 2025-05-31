package tracciacallcenter;



public abstract class CallCenter {

    protected final int N = 20, M = 50;



    public abstract void richiediAssistenza() throws InterruptedException;

    public abstract void fornisciAssistenza() throws InterruptedException;

    public abstract void terminaChiamata() throws InterruptedException;

    public abstract void prossimoCliente() throws InterruptedException;

    public void test(){

        for(int i = 0 ; i < N ; i++){
            new Operatore(this, i).start();
        }
        for(int i = 0 ; i < M ; i++){
            new Cliente(this).start();
        }
    }
}
