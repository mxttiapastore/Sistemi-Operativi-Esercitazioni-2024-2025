package tracciabinanananananananana;

import java.util.concurrent.Semaphore;

public class CantiereFerroviarioSem extends CantiereFerroviario{

    protected final int M = 3;//numero binari sui quali lavorare
    protected final int N = 50;


    private int count = 0;

    private Semaphore mutex = new Semaphore(1);



    private Semaphore[] traverse = new Semaphore[N];
    private Semaphore[] rotaie = new Semaphore[N];
    {
        traverse[0] = new Semaphore(1);
        for(int i = 0 ; i < traverse.length ; i++){
            traverse[i] = new Semaphore(0);
            rotaie[i] = new Semaphore(0);
        }
    }



    public void lavora(int t) throws InterruptedException {
        mutex.acquire();
        while(! (count == N - 1)){
            if(t == TRAVERSA){
                System.out.println("baba");
                traverse[count].acquire();
                System.out.println("L'operaio numero " + Thread.currentThread().getId() + " ha messo la traversa al binario numero " + (count + 1));
            } else{
                rotaie[count].acquire();
                System.out.println("L'operaio numero " + Thread.currentThread().getId() + " ha messo a rotaia al binario numero " + (count + 1));
                count++;
            }
        }


    }

    @Override
    public void termina(int t) throws InterruptedException {
            if(t == TRAVERSA){
                rotaie[count].release();
            }
            if(t == ROTAIA){
                traverse[count+1].release();
            }
        mutex.release();
    }

    public static void main(String[] args) {
        CantiereFerroviario c = new CantiereFerroviarioSem();
        c.test();

    }


}
