package tracciasaltatore;

public class Saltatore extends Thread{
    private Salto salto;
    private int n_maglia;
    public Saltatore(Salto s , int n){
        salto = s;
        n_maglia = n;
    }
    public void run(){
        try{
            salto.inizio(this);
            int pos = salto.arrivo(this);
        }catch (InterruptedException e){}
    }
    public int getN_maglia(){
        return n_maglia;
    }
}
