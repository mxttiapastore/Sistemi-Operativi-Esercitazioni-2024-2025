package tracciamuseo;

import java.util.Random;

public class Visitatore extends Thread{

    private MuseoC museo;



    public Visitatore(MuseoC m){
        museo = m;
    }

    public void run(){
        try{
            museo.visitaSA();
            museo.terminaVisitaSA();
            museo.visitaSD();

            museo.terminaVisitaSD();
        }catch(InterruptedException e){}
    }


}
