package br.com.samirmelo.operacaobancaria;

import br.com.samirmelo.operacaobancaria.service.LoginService;

/**
 * Ponto de entrada da aplicação.
 * Primeiro passo: login antes de acessar operações bancárias.
 */
public class App {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();

        if (loginService.autenticar()) {
            // Aqui vamos chamar o menu de operações na Etapa 3
            System.out.println("Bem-vindo ao Student Bank!");
        } else {
            System.out.println("Encerrando aplicação.");
        }
    }
}
