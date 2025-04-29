package tracciaziendaagricola;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class AziendaAgricolaSem extends AziendaAgricola{

    public AziendaAgricolaSem(){
        super();
    }

    Semaphore mutex = new Semaphore(1);

    Semaphore ricarica = new Semaphore(0);

    public static void main(String[] args) {
        AziendaAgricola a = new AziendaAgricolaSem();
        a.test(100);
    }


    public void caricaSacchetti() throws InterruptedException{
        ricarica.acquire();
        num_sacchi = MAX_SACCHI;
    }
    public void ritiraSacchetti(int num, Cliente c) throws InterruptedException{
        mutex.acquire();
        System.out.println("Il cliente numero " + c.getID() + " vuole ritirare "
        + num + " sacchetti");
        for(int i = 0 ; i < num ; i++){
            if(num_sacchi == 0) {
                ricarica.release();
                attendiRicarica();
            }
            System.out.println("Il cliente " + c.getID() + " sta ritirando un sacchetto");
            Thread.sleep(1000);
            num_sacchi--;
            incasso += 3;
        }
        mutex.release();
    }

    @Override
    public void attendiRicarica() throws InterruptedException {
        Thread.sleep(10000);

    }
}
