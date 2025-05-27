package tracciabowling;

public class Operatore extends Thread{

    private SalaBowling sala;

    public Operatore(SalaBowling s){
        sala = s;
    }

    public void run(){
        try{

        sala.preparaPartita();
        sala.deposita();
        }catch (InterruptedException e){}
    }

}
