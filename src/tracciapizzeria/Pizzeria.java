package tracciapizzeria;

public abstract class Pizzeria {

    protected final int POSTI_A_SEDERE = 5;
    protected final int NUMERO_CLIENTI = 5;






    public abstract void entra() throws InterruptedException;

    public abstract void mangiaPizza() throws InterruptedException;

    public abstract void preparaPizza() throws InterruptedException;

    public abstract void serviPizza() throws InterruptedException;

    public void test(){
        new Pizzaiolo(this).start();
        for(int i = 0 ; i < NUMERO_CLIENTI ; i++){
            new Cliente(this).start();
        }

    }

}
