package tracciacinema;

public class Cliente extends  Thread {

    private Cinema cinema;

    public Cliente(Cinema c) {
        cinema = c;
    }

    public void run() {
        try {
            cinema.acquistaBiglietto();
            cinema.vediFilm();
        } catch (InterruptedException e) {}
    }

}
