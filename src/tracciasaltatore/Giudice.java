package tracciasaltatore;

import java.util.concurrent.TimeUnit;

public class Giudice extends Thread {
    private Salto salto;
    protected final int H = 1;
    public Giudice(Salto s){
        salto = s;
    }
    public void run(){
        try{
            boolean ciSonoAncora = salto.successivo();
            while (ciSonoAncora){
                TimeUnit.MINUTES.sleep(H);
                ciSonoAncora = salto.successivo();
            }
        }catch (InterruptedException e){}
    }
}
