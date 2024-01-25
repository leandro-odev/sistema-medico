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
                Administrador adm = new Administrador("adm", "adm");
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o usuario");
                String usuario = scanner.nextLine();
                System.out.println("Digite a senha");
                String senha = scanner.nextLine();
                if (adm.verificarLogin(usuario, senha)) {
                    System.out.println("Login realizado com sucesso");

                    label:
                    while (true) {
                        System.out.println("Digite 1 para gerenciar tables");
                        System.out.println("Digite 2 para ver tables");
                        System.out.println("Digite 3 para sair");
                        String opcao = scanner.nextLine();
                        switch (opcao) {
                            case "1":
                                System.out.println("Digite 1 para adicionar um medico");
                                System.out.println("Digite 2 para adicionar um paciente");
                                System.out.println("Digite 3 para remover medico");
                                System.out.println("Digite 4 para remover paciente");
                                System.out.println("Digite 5 para sair");
                                String opcao2 = scanner.nextLine();
                                switch (opcao2) {
                                    case "1":
                                        adm.addMedico(conexao);
                                        break;
                                    case "2":
                                        adm.addPaciente(conexao);
                                        break;
                                    case "3":
//                                        adm.removeMedico(conexao);
                                        break;
                                    case "4":
//                                        adm.removePaciente(conexao);
                                        break;
                                    case "5":
                                        break label;
                                    default:
                                        System.err.println("Opcao invalida\n");
                                        break;
                                }
                                break;
                            case "2":
                                System.out.println("Digite 1 para ver os medicos");
                                System.out.println("Digite 2 para ver os pacientes");
                                System.out.println("Digite 3 para sair");
                                String opcao3 = scanner.nextLine();
                                switch (opcao3) {
                                    case "1":
                                        adm.printMedico(conexao);
                                        break;
                                    case "2":
                                        adm.printPaciente(conexao);
                                        break;
                                    case "3":
                                        break label;
                                    default:
                                        System.err.println("Opcao invalida\n");
                                        break;
                                }
                                break;
                            case "3":
                                break label;
                            default:
                                System.err.println("Opcao invalida\nTente novamente");
                                break;
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