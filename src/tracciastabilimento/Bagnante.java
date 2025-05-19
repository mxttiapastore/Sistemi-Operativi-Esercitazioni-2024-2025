package tracciastabilimento;

public class Bagnante extends Thread{

    private Stabilimento stabilimento;

    public Bagnante(Stabilimento s){
        stabilimento = s;
    }

    public void run(){
        try{
            stabilimento.scegliAccesso();
            stabilimento.paga();
        }catch (InterruptedException e){}
    }
}
