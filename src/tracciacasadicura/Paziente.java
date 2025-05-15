package tracciacasadicura;

public class Paziente extends Thread{

    private CasaDiCura casadicura;

    public Paziente(CasaDiCura c){
        casadicura = c;
    }

    public void run(){
        try{
            casadicura.pazienteEntra();
            casadicura.pazienteEsci();

        }catch (InterruptedException e){e.printStackTrace();}

    }
}
