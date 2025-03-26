package produttoreconsumatore;

import java.util.Random;

public class Produttore extends Thread{

    private static final int MAX_RANDOM = 10;
    private static final long MIN_TEMPO_PRODUZIONE = 1;
    private static final int MAX_TEMPO_PRODUZIONE = 10;
    private Random random = new Random();
    private Buffer buffer;
    private int id;

    public Produttore(Buffer b, int id){buffer = b;
    this.id = id;}


    public int getid(){
        return id;
    }
    public void run(){
        try{
            while(true){
                int i = produci();
                buffer.put(i);
                System.out.println("Il produttore numero " + getid() + " ha inserito all'indice " + i);
            }
        }catch (InterruptedException e){}
    }

    private int produci() throws InterruptedException{

        return random.nextInt(MAX_RANDOM);
    }
}
