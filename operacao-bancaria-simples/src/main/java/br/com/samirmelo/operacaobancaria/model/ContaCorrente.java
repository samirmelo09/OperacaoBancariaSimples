package br.com.samirmelo.operacaobancaria.model;

/**
 * Representa uma conta corrente.
 */
public class ContaCorrente extends Conta {
    public ContaCorrente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void exibirTipoConta() {
        System.out.println("Conta Corrente do titular: " + titular);
    }
}
