package tracciapizzeria;

public class Pizzaiolo extends Thread{

    private Pizzeria pizzeria;

    public Pizzaiolo(Pizzeria p){
        pizzeria = p;
    }

    public void run(){

        try{
            while(true) {
                pizzeria.preparaPizza();
                pizzeria.serviPizza();
            }
        }catch (InterruptedException e){}

    }
}
