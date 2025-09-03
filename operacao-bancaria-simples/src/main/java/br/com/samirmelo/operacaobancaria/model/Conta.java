package br.com.samirmelo.operacaobancaria.model;


/**
 * Classe abstrata representando uma conta bancária genérica.
 */
public abstract class Conta {
    protected String titular;
    protected double saldo;

    public Conta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void atualizarSaldo(double valor) {
        this.saldo += valor;
    }

    // Método abstrato que cada tipo de conta pode implementar de forma diferente
    public abstract void exibirTipoConta();


}
