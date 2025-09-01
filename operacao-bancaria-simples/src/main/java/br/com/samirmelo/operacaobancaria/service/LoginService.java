package br.com.samirmelo.operacaobancaria.service;

import java.util.Scanner;

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