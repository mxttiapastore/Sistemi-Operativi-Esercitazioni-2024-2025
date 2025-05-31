package tracciacallcenter;

public class Cliente extends Thread{

    private CallCenter callcenter;

    public Cliente(CallCenter c){
        callcenter = c;
    }

    public void run(){
        try{
            callcenter.richiediAssistenza();
            callcenter.terminaChiamata();
        }catch (InterruptedException e){}
    }

}
