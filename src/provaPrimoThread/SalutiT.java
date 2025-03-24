package provaPrimoThread;

public class SalutiT extends Thread {
    public SalutiT(String nome) {
        super(nome);
    }
    public void run() {
        for(int i = 1; i <= 10; i++) {
            System.out.println("Saluti da " + getName());
            //getName() restituisce ii nome del Thread
        }
    }
}
