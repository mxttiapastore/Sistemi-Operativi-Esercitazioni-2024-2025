package eserciziocontocorrente;

import java.util.Random;

public class Correntista implements Runnable{

    private final static int MIN_ATTESA = 1;
    private final static int MAX_ATTESA = 3;

    private Random random = new Random();
    private ContoCorrente cc;
    private int importo;
    private int numero_operazioni;

    public Correntista(ContoCorrente cc, int importo, int numero_operazioni) {
        if (numero_operazioni % 2 != 0) {
            throw new RuntimeException(
                    "Il numero di operazioni deve essere pari"
            );
        }
        this.cc = cc;
        this.importo = importo;
        this.numero_operazioni = numero_operazioni;
    }

    public void run(){
        try{
            for(int i = 0; i < numero_operazioni; i++){
                attesaCasuale();

                if(i % 2 == 0) cc.deposita(importo); //condizione per depositare

                else cc.preleva(importo); //condizione per prelevare (seconda operazione consecutiva al deposito)

            }

        }catch (InterruptedException e){}

        System.out.println("Il correntista " + Thread.currentThread().getId() +
                " ha terminato le sue operazioni");

    }

    private void attesaCasuale() throws InterruptedException {
        Thread.sleep((random.nextInt(MAX_ATTESA - MIN_ATTESA + 1) + MIN_ATTESA));
    }





}
