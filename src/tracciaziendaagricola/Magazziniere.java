package tracciaziendaagricola;

import java.util.concurrent.TimeUnit;

public class Magazziniere extends Thread {

    private AziendaAgricola azienda;

    public Magazziniere(AziendaAgricola a) { azienda = a; }

    public void run(){
        try{
            azienda.caricaSacchetti();
            System.out.println("Il magazziniere sta ricaricando il magazzino..");
            ricarica();
            System.out.println("Il magazziniere ha ricaricato il magazzino");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ricarica() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
    }
}
