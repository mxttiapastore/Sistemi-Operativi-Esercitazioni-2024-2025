package produttoreconsumatore;

import java.util.concurrent.TimeUnit;

public class Consumatore extends Thread{

    private Buffer buffer;

    public Consumatore(Buffer b){buffer = b;}

    public void run(){
        try{
            while (true){
                int i = buffer.get();
                consuma(i);
                System.out.println("Il consumatore numero "+ getId() + " ha prelevato dall'indice " + i);
            }
        }catch (InterruptedException e){}
    }

    private void consuma(int i) throws InterruptedException{
        TimeUnit.SECONDS.sleep(i);
    }
}
