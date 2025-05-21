package tracciabancocheckin;

public class Addetto extends Thread{

    private BancoCheckIn banco;

    public Addetto(BancoCheckIn b){
        banco = b;
    }

    public void run(){
        try{
            while(true) {
                banco.pesaERegistra();
                banco.prossimoPasseggero();
            }
        }catch (InterruptedException e){
        }
    }
}
