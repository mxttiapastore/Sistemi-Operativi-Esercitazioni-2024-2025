package tracciabowling;

public class Giocatore extends Thread{

    private SalaBowling sala;

    public Giocatore(SalaBowling s){
        sala = s;
    }

    public void run(){

        try{
            sala.fornisciInformazioni();
            for(int i = 0 ; i < 10; i++){
                sala.gioca();
            }
        }catch (InterruptedException e){}

    }
}
