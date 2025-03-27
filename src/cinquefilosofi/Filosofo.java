package cinquefilosofi;

public class Filosofo extends Thread {

    private Tavolo tavolo;
    private int posizione;

    public Filosofo(Tavolo t , int pos){
        tavolo = t;
        posizione = pos;
    }


    public void run(){
        try{
            while (true){
                tavolo.prendiBacchette(posizione);
                System.out.println("Il filosofo " + posizione + " ha iniziato a mangiare");
                Thread.sleep(1000);
                System.out.println("Il filosofo " + posizione + " ha smesso di mangiare");
                tavolo.rilasciaBacchette(posizione);
                Thread.sleep(1000);
                //pensa
            }
        }catch(InterruptedException e){e.printStackTrace();}
    }
}
