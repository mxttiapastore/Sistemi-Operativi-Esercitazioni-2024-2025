package tracciafunivia;

import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{

    private Funivia funivia;

    private int tempoSalita = 5;
    private int tempoDiscesa = 2;

    public Pilota(Funivia f){
        funivia = f;
    }

    public void run(){
        try{
            while(true){
                funivia.pilotaStart();
                attendi(tempoSalita);
                funivia.pilotaEnd();
                attendi(tempoDiscesa);
            }
        }catch (InterruptedException e){e.printStackTrace();}
    }

    private void attendi(int waiting_time) throws InterruptedException{
        TimeUnit.SECONDS.sleep(waiting_time);
    }
}
