package tracciacocktailbar;

public abstract class Bar{

    protected int incasso;

    protected final int COSTO_COKTAIL_NORMALE = 6;
    protected final int COSTO_COCKTAIL_SPECIALE = 8;
    protected final int NUMERO_CLIENTI = 5;




    public abstract void ordinaCocktail(int tipo) throws InterruptedException;

    public abstract void preparaCocktail() throws InterruptedException;

    public abstract void beviEPaga(int tipo) throws InterruptedException;

    public abstract void chiudiBar() throws InterruptedException;



    public void test(){

        for(int i  = 0 ; i < NUMERO_CLIENTI ; i++) new Cliente(this).start();

        new Barman(this).start();
    }




}
