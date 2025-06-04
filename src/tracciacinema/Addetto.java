package tracciacinema;

public class Addetto extends Thread{

    private Cinema cinema;

    public Addetto(Cinema c){
        cinema = c;
    }

    public void run(){
        try{

            while(!cinema.consegnaBiglietto()){}

            cinema.chiudiCinema();

        }catch (InterruptedException e){}
    }

}
