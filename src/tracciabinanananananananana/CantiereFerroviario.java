package tracciabinanananananananana;

public abstract class CantiereFerroviario {

    protected final int NUMERO_OPERAI = 4;
    protected final int TRAVERSA = 0, ROTAIA = 1;


    public abstract void lavora(int t) throws InterruptedException;

    public abstract void termina(int t) throws InterruptedException;

    public void test(){

        for(int i = 0 ; i < NUMERO_OPERAI ; i++){
            new Operaio(this, TRAVERSA).start();
            new Operaio(this, ROTAIA).start();
        }
    }


}
