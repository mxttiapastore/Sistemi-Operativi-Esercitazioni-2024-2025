package tracciabowling;

public abstract class SalaBowling {


    public abstract String fornisciInformazioni() throws InterruptedException;

    public abstract void preparaPartita() throws InterruptedException;

    public abstract void gioca() throws InterruptedException;

    public abstract void deposita() throws InterruptedException;


    public void test(){
        new Operatore(this).start();
        for(int i = 0 ; i < 4 ; i++){
            new Giocatore(this).start();
        }

    }


}
