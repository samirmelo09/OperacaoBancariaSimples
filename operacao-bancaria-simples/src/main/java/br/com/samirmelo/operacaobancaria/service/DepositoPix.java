package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

public class DepositoPix extends Deposito {
    public DepositoPix(Conta conta, double valor) {
        super(conta, valor);
    }

    @Override
    protected String getTipoDeposito() {
        return "Pix";
    }
}
