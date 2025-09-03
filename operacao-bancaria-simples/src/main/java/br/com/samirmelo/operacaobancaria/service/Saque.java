package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;

/**
 * Operação de saque.
 */
public class Saque implements OperacaoBancaria {
    private Conta conta;
    private double valor;

    public Saque(Conta conta, double valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void executar() {
        if (valor > 0 && valor <= conta.getSaldo()) {
            conta.atualizarSaldo(-valor); // ou conta.sacar(valor) se usar a refatoração
            System.out.printf("Saque de R$ %.2f realizado com sucesso, seu saldo atual é de R$ %.2f%n",
                    valor, conta.getSaldo());
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
        }
    }
}