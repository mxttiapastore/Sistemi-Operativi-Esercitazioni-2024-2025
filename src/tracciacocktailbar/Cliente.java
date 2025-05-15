package tracciacocktailbar;

import java.util.Random;

public class Cliente extends Thread{

    private Bar bar;
    private Random random = new Random();


    public Cliente(Bar bar){
        this.bar = bar;
    }

    public void run(){

        int scelta = random.nextInt(2);
        try {
            bar.ordinaCocktail(scelta);

            bar.beviEPaga(scelta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
