package esempiocronometro;

import java.util.Scanner;

public class AzionatoreCronometro {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cronometro cronometro = new Cronometro();
        System.out.println("Invio per cominciare");
        in.nextLine();
        cronometro.start();
        System.out.println("Invio per terminare");
        in.nextLine();
        cronometro.interrupt();
        System.out.println("Programma terminato");
    }
}
