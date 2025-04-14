package cinquefilosofilock;

public class Filosofo extends Thread{
    private Tavolo tavolo;
    private int posizione;

    public Filosofo(Tavolo t, int pos){
        tavolo = t;
        posizione = pos;
    }

    public void run(){
        try{
            while(true){
                tavolo.prendiBacchette(posizione);
                System.out.println("Il filosofo " + posizione +
                        " ha iniziato a mangiare");
                mangia();
                System.out.println("Il filosofo " + posizione +
                        " ha finito di mangiare");
                tavolo.rilasciaBacchette(posizione);
                pensa();
            }
        }catch (InterruptedException e){e.printStackTrace();}
    }

    private void mangia() throws InterruptedException
    {Thread.sleep(10000);}
    private void pensa() throws InterruptedException
    {Thread.sleep(5000);}
}
