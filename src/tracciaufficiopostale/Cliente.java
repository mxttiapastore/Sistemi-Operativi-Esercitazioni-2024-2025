package tracciaufficiopostale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente extends Thread {

    private String[] operazioni = {"A","B", "C"};

    private UfficioPostale ufficio;
    private String operazione;

    private Random rand = new Random();

    public Cliente(UfficioPostale u){
        ufficio = u;
        operazione = scegliOperazione();
    }

    public void run(){
        try{
            boolean esito = ufficio.ritiraTicket(operazione);
            if(esito) ufficio.attendiSportello(operazione);
            else System.out.println("Il cliente " + Thread.currentThread().getId() + " ha lasciato l'ufficio postale");


        }catch (InterruptedException e){}



    }

    private String scegliOperazione(){
        int scelta = rand.nextInt(operazioni.length);
        return operazioni[scelta];

    }
}
