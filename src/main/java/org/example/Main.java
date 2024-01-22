package org.example;

import org.example.Classes.Administrador;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conexao = Conexao.conectar();

        if (conexao != null) {
            try {
                Administrador adm = new Administrador("adm", "adm123");
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o usuario");
                String usuario = scanner.nextLine();
                System.out.println("Digite a senha");
                String senha = scanner.nextLine();
                if (adm.verificarLogin(usuario, senha)) {
                    System.out.println("Login realizado com sucesso");

                    while (true) {
                        System.out.println("Digite 1 para adicionar um medico");
                        System.out.println("Digite 2 para printar os medicos");
                        System.out.println("Digite 3 para sair");
                        String opcao = scanner.nextLine();
                        if (opcao.equals("1")) {
                            System.out.println("Digite o nome do medico");
                            String nome = scanner.nextLine();
                            System.out.println("Digite o nascimento do medico");
                            String nascimento = scanner.nextLine();
                            System.out.println("Digite o crm do medico");
                            String crm = scanner.nextLine();
                            System.out.println("Digite o genero do medico");
                            String genero = scanner.nextLine();
                            adm.addMedico(nome, nascimento, crm, genero, conexao);
                        }
                        else if (opcao.equals("2")) {
                            adm.printMedicos(conexao);
                        }
                        else if (opcao.equals("3")) {
                            break;
                        }
                        else {
                            System.err.println("Opcao invalida\nTente novamente");
                        }
                    }

                } else {
                    System.err.println("Usuario ou senha incorretos\nEncerrando programa");
                }

            }
//            catch (SQLException e) {
//                System.err.println("Erro na conexão com o Banco de Dados: " + e.getMessage());
//            }
            finally {
                Conexao.desconectar(conexao);
            }
        }
        else {
            System.err.println("Falha ao conectar ao banco de dados.");
        }
    }
}