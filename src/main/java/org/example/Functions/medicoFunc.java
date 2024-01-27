package org.example.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class medicoFunc {

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

    public void removeMedico (Connection conexao){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o CRM do medico");
            String crm = scanner.nextLine();

            String sql = "DELETE FROM medico WHERE crm_medico = ?";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
            preparedStatement1.setString(1, crm);
            preparedStatement1.execute();
            System.out.println("Medico removido com sucesso");
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para remover medico: " + e.getMessage());
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
