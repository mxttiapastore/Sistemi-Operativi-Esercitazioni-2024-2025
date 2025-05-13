package tracciabarmod;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{

    private Random random = new Random();
    private BarMod bar;

    public Cliente(BarMod bar){
        this.bar = bar;
    }


    public void run(){
        try {
            int scelta = bar.scegli();
            bar.inizia(scelta);

            for(int i = 0 ; i < 2; i++){
                if(scelta == 0){
                    System.out.println("Il cliente " + Thread.currentThread().getId() + " sta bevendo il caffé...");
                    TimeUnit.SECONDS.sleep(random.nextInt(40-20+1)+20);
                }else{
                    System.out.println("Il cliente " + Thread.currentThread().getId() + " sta pagando");
                    TimeUnit.SECONDS.sleep(random.nextInt(10-5+1)+5);
                }
                bar.finisci(scelta);
                scelta = 1- scelta;
            }
            System.out.println("Il cliente " + Thread.currentThread().getId() + " ha lasciato il bar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
