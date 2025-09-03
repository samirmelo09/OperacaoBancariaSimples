# 📘 OperacaoBancariaSimples
Sistema de simulação de operações bancárias com Saque, Depósito, Consulta de saldo, aplicando conceitos de POO: abstração, herança, polimorfismo e interfaces.

# Estrutura do Projeto

br.com.samirmelo.operacaobancaria
├── model
│   ├── Conta.java
│   └── ContaCorrente.java
└── service
    ├── Deposito.java
    ├── DepositoEspecie.java
    ├── DepositoPix.java
    ├── DepositoTransferencia.java
    ├── EmitirSaldo.java
    ├── LoginService.java
    ├── MenuService.java
    ├── OperacaoBancaria.java
    └── Saque.java
App.java

# 📄 Código e documentação linha a linha

# 1️⃣ Conta.java
public abstract class Conta {
    protected String titular; // nome do titular da conta
    protected double saldo;   // saldo atual da conta

    // Construtor inicializa titular e saldo inicial
    public Conta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular; // retorna o nome do titular
    }

    public double getSaldo() {
        return saldo; // retorna o saldo atual
    }

    // Atualiza o saldo da conta, positivo para depósito, negativo para saque
    public void atualizarSaldo(double valor) {
        this.saldo += valor;
    }

    // Método abstrato: cada tipo de conta deve implementar seu tipo
    public abstract void exibirTipoConta();
}

✅ Abstração: classe não instanciável diretamente, apenas via subclasses.
--------------------------------------------------------------------------

# 2️⃣ ContaCorrente.java
public class ContaCorrente extends Conta {

    public ContaCorrente(String titular, double saldoInicial) {
        super(titular, saldoInicial); // chama construtor da superclasse
    }

    @Override
    public void exibirTipoConta() {
        System.out.println("Conta Corrente do titular: " + titular);
    }
}

✅ Herança: ContaCorrente herda Conta.
✅ Polimorfismo: exibirTipoConta() implementa o método abstrato.
------------------------------------------------------------------------

# 3️⃣ OperacaoBancaria.java (interface)
public interface OperacaoBancaria {
    void executar(); // contrato: todas as operações bancárias devem implementar executar()
}

✅ Interface: garante que saque, depósito e emissão de saldo tenham o método executar().
------------------------------------------------------------------------

# 4️⃣ Deposito.java (abstrata)
public abstract class Deposito implements OperacaoBancaria {
    protected Conta conta;  // conta alvo do depósito
    protected double valor; // valor do depósito

    public Deposito(Conta conta, double valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void executar() {
        if (valor > 0) {
            conta.atualizarSaldo(valor); // atualiza saldo
            System.out.printf("Depósito de R$ %.2f realizado via %s, seu saldo atual é de R$ %.2f%n",
                    valor, getTipoDeposito(), conta.getSaldo());
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    protected abstract String getTipoDeposito(); // cada tipo de depósito implementa
}

✅ Polimorfismo: subclasses DepositoPix, DepositoEspecie, DepositoTransferencia implementam getTipoDeposito().
--------------------------------------------------------------------------------------------------------------

# 5️⃣ Subclasses de depósito

- DepositoPix.java → retorna "Pix"
- DepositoTransferencia.java → retorna "Transferência Bancária"
- DepositoEspecie.java → retorna "Depósito em Espécie"
-------------------------------------------------------------------------------------------------------------

# 6️⃣ Saque.java
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
            conta.atualizarSaldo(-valor);
            System.out.printf("Saque de R$ %.2f realizado com sucesso, seu saldo atual é de R$ %.2f%n",
                    valor, conta.getSaldo());
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
        }
    }
}

✅ Reutiliza atualizarSaldo(-valor) para saque.
✅ Saída formata valor e saldo com printf.
--------------------------------------------------------------------------------------------

# 7️⃣ EmitirSaldo.java
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
-------------------------------------------------------------------------------------------------

# 8️⃣ LoginService.java
public class LoginService {
    private static final String LOGIN_VALIDO = "student1";
    private static final String SENHA_VALIDA = "12345";

    public boolean autenticar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- Student Bank ----");
        System.out.println("Digite suas credenciais:");

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (LOGIN_VALIDO.equals(login) && SENHA_VALIDA.equals(senha)) {
            System.out.println("Login realizado com sucesso!\n");
            return true;
        } else {
            System.out.println("Credenciais inválidas. Acesso negado.");
            return false;
        }
    }
}

✅ Autenticação simples via console.
-----------------------------------------------------------------------------

# 9️⃣ MenuService.java
public class MenuService {
    private Conta conta;
    private Scanner scanner;

    public MenuService(String titular) {
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
            scanner.nextLine();

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
--------------------------------------------------------------------------
# 1️⃣0️⃣ App.java
public class App {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();

        if (loginService.autenticar()) {
            MenuService menu = new MenuService("Student1");
            menu.exibirMenu();
        } else {
            System.out.println("Encerrando aplicação.");
        }
    }
}
--------------------------------------------------------------------------------




