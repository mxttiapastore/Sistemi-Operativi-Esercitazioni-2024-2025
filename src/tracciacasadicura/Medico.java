package tracciacasadicura;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Medico extends Thread{

    private CasaDiCura casadicura;
    private Random rand = new Random();
    protected final int MIN_OPERAZIONE = 20, MAX_OPERAZIONE = 40;
    protected final int ATTESA_FINALE = 20;

    public Medico(CasaDiCura c){
        casadicura = c;
    }

    public void run(){
        try{
            while (true){
                casadicura.chiamaEIniziaOperazione();
                TimeUnit.SECONDS.sleep(rand.nextInt(MAX_OPERAZIONE - MIN_OPERAZIONE + 1)+ MIN_OPERAZIONE);
                casadicura.fineOperazione();
                TimeUnit.SECONDS.sleep(ATTESA_FINALE);
            }
        }catch(InterruptedException e){e.printStackTrace();}




    }
}
