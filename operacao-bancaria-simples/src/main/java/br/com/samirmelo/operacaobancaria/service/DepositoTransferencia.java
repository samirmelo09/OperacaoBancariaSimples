package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

public class DepositoTransferencia extends Deposito {
    public DepositoTransferencia(Conta conta, double valor) {
        super(conta, valor);
    }

    @Override
    protected String getTipoDeposito() {
        return "Transferência Bancária";
    }
}
