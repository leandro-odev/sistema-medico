package org.example.Classes;

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

    public void addMedico (String nome, String nascimento, String crm, String genero, Connection conexao){
        try {
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

    public void printMedicos(Connection conexao){
        try {
            Scanner scanner = new Scanner(System.in);
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

}
