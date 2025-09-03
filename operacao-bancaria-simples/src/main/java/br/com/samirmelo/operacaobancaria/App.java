package br.com.samirmelo.operacaobancaria;

import br.com.samirmelo.operacaobancaria.service.LoginService;
import br.com.samirmelo.operacaobancaria.service.MenuService;

/**
 * Ponto de entrada da aplicação.
 */
public class App {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();

        if (loginService.autenticar()) {
            // Cria menu passando o titular da conta
            MenuService menu = new MenuService("Student1");
            menu.exibirMenu();
        } else {
            System.out.println("Encerrando aplicação.");
        }
    }
}
