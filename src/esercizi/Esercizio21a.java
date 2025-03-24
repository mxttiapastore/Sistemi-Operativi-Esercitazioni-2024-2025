package esercizi;

public class Esercizio21a {

    private static String nome = "Ciao";

    public static void main(String[] args)
                    throws InterruptedException{
        new Esercizio21a().prova();
        System.out.println(nome);
    }

    public void prova(){
        new Esercizio21aT().start();
        nome = nome + " mondo";
    }

    class Esercizio21aT extends Thread{
        public void run(){
            nome += " 1 2 3";
        }
    }
}
