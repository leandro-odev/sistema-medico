package org.example.Classes;

//import org.example.Conexao;
import org.example.Functions.medicoFunc;
import org.example.Functions.pacienteFunc;
import org.example.Functions.consultaFunc;

import java.sql.Connection;
import java.util.Scanner;

public class Administrador {
    private final String usuario;
    private final String senha;

    public Administrador(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    public boolean verificarLogin(String usuario, String senha){
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    // Printar

    public void printConsulta(Connection conexao) {
        consultaFunc consultaFunc = new consultaFunc();
        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todas as consultas");
        System.out.println("Digite 2 para printar uma consulta especifica");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                consultaFunc.printAllConsulta(conexao);
                break;
            case "2":
                consultaFunc.printOneConsulta(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\nTente novamente");
                break;
        }
    }

    public void printMedico(Connection conexao){
        medicoFunc medicoFunc = new medicoFunc();
        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todos os medicos");
        System.out.println("Digite 2 para printar um medico especifico");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                medicoFunc.printAllMedico(conexao);
                break;
            case "2":
                medicoFunc.printOneMedico(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\nTente novamente");
                break;
        }
    }

    public void printPaciente(Connection conexao) {
        pacienteFunc pacienteFunc = new pacienteFunc();
        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todos os pacientes");
        System.out.println("Digite 2 para printar um pacientes especifico");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                pacienteFunc.printAllPaciente(conexao);
                break;
            case "2":
                pacienteFunc.printOnePaciente(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\n");
                break;
        }
    }

}
