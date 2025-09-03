package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

public class DepositoEspecie extends Deposito {
    public DepositoEspecie(Conta conta, double valor) {
        super(conta, valor);
    }

    @Override
    protected String getTipoDeposito() {
        return "Deposito em Espécie";
    }
}
