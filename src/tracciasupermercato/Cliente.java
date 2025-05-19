package tracciasupermercato;

import java.util.Random;

public class Cliente extends Thread{

    protected final int PRODOTTI_MIN = 1, PRODOTTI_MAX = 50;

    protected Random numero_prodotti = new Random();

    private Supermercato supermercato;

    public Cliente(Supermercato s){
        supermercato = s;
    }

    public void run(){
        try{
            int p = numero_prodotti.nextInt((PRODOTTI_MAX - PRODOTTI_MIN + 1)+ PRODOTTI_MIN);
            int id_cassa = supermercato.getIdCassa();
            supermercato.consegnaProdotti(id_cassa , p);

        } catch (InterruptedException e) {}
    }
}
