package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

/**
 * Classe base para depósitos (abstrata).
 */
public abstract class Deposito implements OperacaoBancaria {
    protected Conta conta;
    protected double valor;

    public Deposito(Conta conta, double valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void executar() {
        if (valor > 0) {
            conta.atualizarSaldo(valor);
            System.out.println("Depósito de R$ " + valor + " realizado via " + getTipoDeposito());
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    // Cada tipo de depósito vai dizer seu nome
    protected abstract String getTipoDeposito();
}