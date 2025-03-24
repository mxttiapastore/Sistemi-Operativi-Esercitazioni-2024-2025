package eserciziocontocorrente;

public abstract class ContoCorrente {

    protected int deposito;

    public ContoCorrente(int deposito_iniziale) {
        this.deposito = deposito_iniziale;
    }

    public abstract void preleva(int importo);

    public abstract void deposita(int importo);

    public int getDeposito() {
        return deposito;
    }

}
