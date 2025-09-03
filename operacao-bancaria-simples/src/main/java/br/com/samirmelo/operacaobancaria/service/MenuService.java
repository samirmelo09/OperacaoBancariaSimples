package br.com.samirmelo.operacaobancaria.service;

import br.com.samirmelo.operacaobancaria.model.Conta;
import br.com.samirmelo.operacaobancaria.model.ContaCorrente;

import java.util.Scanner;

/**
 * Classe responsável por exibir o menu e gerenciar as operações bancárias.
 */
public class MenuService {

    private Conta conta; // Conta do usuário logado
    private Scanner scanner;

    public MenuService(String titular) {
        // Por simplicidade, criamos uma Conta Corrente com saldo inicial de 1000
        this.conta = new ContaCorrente(titular, 1000.0);
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---- MENU DE OPERAÇÕES ----");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Emitir Saldo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> sacar();
                case 2 -> depositar();
                case 3 -> emitirSaldo();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void sacar() {
        System.out.print("Digite o valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        new Saque(conta, valor).executar();
    }

    private void depositar() {
        System.out.println("Escolha o tipo de depósito:");
        System.out.println("1 - Pix");
        System.out.println("2 - Transferência Bancária");
        System.out.println("3 - Espécie");
        System.out.print("Opção: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Deposito deposito;
        switch (tipo) {
            case 1 -> deposito = new DepositoPix(conta, valor);
            case 2 -> deposito = new DepositoTransferencia(conta, valor);
            case 3 -> deposito = new DepositoEspecie(conta, valor);
            default -> {
                System.out.println("Tipo de depósito inválido.");
                return;
            }
        }
        deposito.executar();
    }

    private void emitirSaldo() {
        new EmitirSaldo(conta).executar();
    }
}
