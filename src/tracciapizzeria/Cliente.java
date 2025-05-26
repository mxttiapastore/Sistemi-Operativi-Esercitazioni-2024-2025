package tracciapizzeria;

public class Cliente extends Thread{

    private Pizzeria pizzeria;

    public Cliente(Pizzeria p){
        pizzeria = p;
    }

    public void run(){

        try{
            pizzeria.entra();
            pizzeria.mangiaPizza();
        }catch(InterruptedException e){}


    }

}
