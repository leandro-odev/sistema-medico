package org.example.Classes;

import org.example.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    // Adicionar a tables
    public void addMedico (Connection conexao){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do medico");
            String nome = scanner.nextLine();
            System.out.println("Digite o nascimento do medico");
            String nascimento = scanner.nextLine();
            System.out.println("Digite o crm do medico");
            String crm = scanner.nextLine();
            System.out.println("Digite o genero do medico");
            String genero = scanner.nextLine();


            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataUtil = formato.parse(nascimento);
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());


            String sql = "INSERT INTO medico (nome_medico, nascimento_medico, crm_medico, genero_medico) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
            preparedStatement1.setString(1, nome);
            preparedStatement1.setDate(2, dataSql);
            preparedStatement1.setString(3, crm);
            preparedStatement1.setString(4, genero);
            preparedStatement1.execute();
            System.out.println("Medico adicionado com sucesso");
        }
        catch (ParseException | SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para adicionar medico: " + e.getMessage());
        }
    }

    public void addPaciente (Connection conexao){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do paciente");
            String nome = scanner.nextLine();
            System.out.println("Digite o nascimento do paciente");
            String nascimento = scanner.nextLine();
            System.out.println("Digite o cpf do paciente");
            String cpf = scanner.nextLine();
            System.out.println("Digite o genero do paciente");
            String genero = scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("Erro na conexão com o Banco de Dados para adicionar paciente: " + e.getMessage());
        }
    }


    // Printar medico
    public void printMedico(Connection conexao){

        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todos os medicos");
        System.out.println("Digite 2 para printar um medico especifico");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                printAllMedico(conexao);
                break;
            case "2":
                printOneMedico(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\nTente novamente");
                break;
        }
    }

    public void printAllMedico(Connection conexao) {
        try {
            String consultaSQL = "SELECT * FROM medico";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idMedico = resultado.getInt("id_medico");
                String nomeMedico = resultado.getString("nome_medico");
                String nascimentoMedico = resultado.getString("nascimento_medico");
                String crmMedico = resultado.getString("crm_medico");
                String generoMedico = resultado.getString("genero_medico");

                System.out.println("ID: " + idMedico + ", Nome: " + nomeMedico + ", Nascimento: " + nascimentoMedico + ", CRM: " + crmMedico + ", Genero: " + generoMedico);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar medico: " + e.getMessage());
        }
    }

    public void printOneMedico(Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o CRM do medico");
            String id = scanner.nextLine();
            String consultaSQL = "SELECT * FROM medico WHERE crm_medico = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            preparedStatement.setString(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idMedico = resultado.getInt("id_medico");
                String nomeMedico = resultado.getString("nome_medico");
                String nascimentoMedico = resultado.getString("nascimento_medico");
                String crmMedico = resultado.getString("crm_medico");
                String generoMedico = resultado.getString("genero_medico");

                System.out.println("ID: " + idMedico + ", Nome: " + nomeMedico + ", Nascimento: " + nascimentoMedico + ", CRM: " + crmMedico + ", Genero: " + generoMedico);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar medico: " + e.getMessage());
        }
    }

}
