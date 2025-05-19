package tracciabinanananananananana;

import java.util.concurrent.TimeUnit;

public class Operaio extends Thread{

    protected final int TRAVERSA = 0, ROTAIA = 1, TEMPO_TRAVERSA = 20, TEMPO_ROTAIA = 30;
    private int tipo;
    private CantiereFerroviario cantiereferroviario;

    public Operaio (CantiereFerroviario c, int t){
        cantiereferroviario = c;
        tipo = t;
    }

    public void run(){
        try {
            while(true) {
                TimeUnit.SECONDS.sleep(tipo == TRAVERSA ? TEMPO_TRAVERSA : TEMPO_ROTAIA);
                cantiereferroviario.lavora(tipo);
                cantiereferroviario.termina(tipo);
                TimeUnit.SECONDS.sleep(10);
            }
        }catch (InterruptedException e){e.printStackTrace();}
    }

}
