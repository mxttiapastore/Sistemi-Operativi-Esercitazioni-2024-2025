package eserciziocontocorrente;

import java.util.concurrent.atomic.AtomicInteger;

public class ContoCorrenteAI extends ContoCorrente {

    private AtomicInteger deposito;

    public ContoCorrenteAI(int depositoIniziale) {
        super(depositoIniziale);
        this.deposito = new AtomicInteger(depositoIniziale);
    }

    public void deposito(int importo) {
        deposito.addAndGet(importo);
    }

    public void preleva(int importo){
        deposito.addAndGet(-importo);
    }

    @Override
    public void deposita(int importo) {}

    public int getDeposito() {
        return deposito.get();
    }
}
