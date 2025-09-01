package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

/**
 * Operação para consultar o saldo da conta.
 */
public class EmitirSaldo implements OperacaoBancaria {
    private Conta conta;

    public EmitirSaldo(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void executar() {
        System.out.println("Saldo atual de " + conta.getTitular() + ": R$ " + conta.getSaldo());
    }
}
