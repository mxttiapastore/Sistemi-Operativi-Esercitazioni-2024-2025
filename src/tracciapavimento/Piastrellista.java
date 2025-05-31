package tracciapavimento;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class Piastrellista extends Thread{


    protected final int A = 0, B = 1;


    private Pavimento pavimento;
    private int tipo;

    public Piastrellista(Pavimento p, int t){
        pavimento = p;
        tipo = t;
    }

    public void run(){

        try{
            System.out.println("Il piastrellista di tipo " + (tipo == A ? "A" : "B") +  " (" + Thread.currentThread().getName() + ") sta preparando l'attrezzatura..");
            TimeUnit.SECONDS.sleep(30); //replace un minuto
            String blocco = pavimento.inizia(tipo);
            pavimento.finisci(tipo , blocco);
            System.out.println("Il piastrellista di tipo " + (tipo == A ?"A" : "B") + " (" + Thread.currentThread().getName() + ") ha posato " +
                    (tipo == A ? "la colla" : "la piastrella") + " e ora si riposa");
            TimeUnit.SECONDS.sleep(30);// replace un minuto

        }catch (InterruptedException e){e.printStackTrace();}







    }



}
