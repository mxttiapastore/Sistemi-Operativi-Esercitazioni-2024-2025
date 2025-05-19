package tracciastabilimento;

public class Gestore extends Thread{

    private Stabilimento stabilimento;

    public Gestore(Stabilimento s){
        stabilimento = s;
    }

    public void run(){
        try{
            stabilimento.preparaPostazioni();
            stabilimento.chiusura();
        }catch (InterruptedException e){}

    }
}
