package tracciafunivia;

public abstract class Funivia {

    protected final int TURISTA_A_PIEDI = 0;
    protected final int TURISTA_IN_BICI = 1;

    protected final int NUMERO_POSTI_FUNIVIA = 6;


    public abstract void pilotaStart() throws InterruptedException;

    public abstract void pilotaEnd() throws InterruptedException;

    public abstract void turistaSali(int n) throws InterruptedException;

    public abstract void turistaScendi(int n) throws InterruptedException;


    public void test(int num_turisti_piedi , int num_turisti_bici){

        for(int i = 0 ; i < num_turisti_bici ; i++){
            new Turista(this, TURISTA_IN_BICI).start();
        }

        for(int i = 0 ; i < num_turisti_piedi ; i++){
            new Turista(this, TURISTA_A_PIEDI).start();
        }
        new Pilota(this).start();
    }


}
