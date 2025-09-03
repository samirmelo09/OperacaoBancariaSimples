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
            conta.atualizarSaldo(valor); // ou conta.depositar(valor) se usar a refatoração
            // printf com 2 casas decimais para o valor e saldo
            System.out.printf("Depósito de R$ %.2f realizado via %s, seu saldo atual é de R$ %.2f%n",
                    valor, getTipoDeposito(), conta.getSaldo());
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    // Cada tipo de depósito vai dizer seu nome
    protected abstract String getTipoDeposito();
}