package tracciasupermercato;

public abstract class Supermercato {



    public abstract int getIdCassa() throws InterruptedException;

    public abstract void consegnaProdotti(int id , int p) throws InterruptedException;

    public abstract int  segnalaCassaLibera(int id) throws InterruptedException;

    public abstract void congedaCliente(int id) throws InterruptedException;


}
