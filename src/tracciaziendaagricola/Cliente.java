package tracciaziendaagricola;

import java.util.Random;

public class Cliente extends Thread{

    private int ID;
    private AziendaAgricola azienda;

    public Cliente(AziendaAgricola a, int i){
        azienda = a;
        ID = i;
    }

    public void run(){
        try{

            int numero = new Random().nextInt(1,11);
                azienda.ritiraSacchetti(numero, this);
            } catch (InterruptedException ex) {
                ex.printStackTrace();}


    }

    public int getID(){
        return ID;
    }

}


