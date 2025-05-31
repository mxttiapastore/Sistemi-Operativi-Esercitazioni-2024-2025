package tracciacallcenter;

public class Operatore extends Thread{

    private CallCenter callcenter;
    private int ID;

    public Operatore(CallCenter c , int id){
        callcenter = c;
        ID = id;
    }

    public void run(){
        try{
            while (true){
                callcenter.fornisciAssistenza();
                callcenter.prossimoCliente();
            }
        }catch (InterruptedException e){}
    }

    public int getID(){
        return ID;
    }

}
