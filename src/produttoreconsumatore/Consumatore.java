package produttoreconsumatore;

import java.util.concurrent.TimeUnit;

public class Consumatore extends Thread{

    private Buffer buffer;
    private int id ;

    public Consumatore(Buffer b, int id){buffer = b;
        this.id = id ; }

    public int getid(){
        return id;
    }


    public void run(){
        try{
            while (true){
                int i = buffer.get();
                consuma(i);
                System.out.println("Il consumatore numero "+ getid() + " ha prelevato dall'indice " + i);
            }
        }catch (InterruptedException e){}
    }

    private void consuma(int i) throws InterruptedException{
        TimeUnit.SECONDS.sleep(i);
    }
}
