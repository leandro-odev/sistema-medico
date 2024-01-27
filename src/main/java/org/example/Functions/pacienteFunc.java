package org.example.Functions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class pacienteFunc {

    public void addPaciente (Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do paciente");
            String nome = scanner.nextLine();
            System.out.println("Digite o nascimento do paciente");
            String nascimento = scanner.nextLine();
            String cpf;
            while (true) {
                System.out.println("Digite o cpf do paciente");
                cpf = scanner.nextLine();
                if (cpf.equals("sair")) {
                    return;
                }
                else if (BasicFunctions.verifyCpf(cpf)) {
                    break;
                }

                System.err.println("CPF invalido\nTente novamente ou digite 'sair' para sair");
            }
            System.out.println("Digite o genero do paciente");
            String genero = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataUtil = formato.parse(nascimento);
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

            String sql = "INSERT INTO paciente (nome_paciente, nascimento_paciente, cpf_paciente, genero_paciente) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
            preparedStatement1.setString(1, nome);
            preparedStatement1.setDate(2, dataSql);
            preparedStatement1.setString(3, cpf);
            preparedStatement1.setString(4, genero);
            preparedStatement1.execute();
            System.out.println("Paciente adicionado com sucesso");
        }
        catch (Exception e) {
            System.err.println("Erro na conexão com o Banco de Dados para adicionar paciente: " + e.getMessage());
        }
    }

    public void removePaciente (Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o CPF do paciente");
            String cpf = scanner.nextLine();

            String sql = "DELETE FROM paciente WHERE cpf_paciente = ?";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
            preparedStatement1.setString(1, cpf);
            preparedStatement1.execute();
            System.out.println("Paciente removido com sucesso");
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para remover paciente: " + e.getMessage());
        }
    }

    public void printAllPaciente (Connection conexao) {
        try {
            String consultaSQL = "SELECT * FROM paciente";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                String nomeMedico = resultado.getString("nome_paciente");
                int idPaciente = resultado.getInt("id_paciente");
                String nascimentoPaciente = resultado.getString("nascimento_paciente");
                String cpfPaciente = resultado.getString("cpf_paciente");
                String generoPaciente = resultado.getString("genero_paciente");

                System.out.println("ID: " + idPaciente + ", Nome: " + nomeMedico + ", Nascimento: " + nascimentoPaciente + ", CPF: " + cpfPaciente + ", Genero: " + generoPaciente);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar paciente: " + e.getMessage());
        }
    }

    public void printOnePaciente (Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o CPF do paciente");
            String id = scanner.nextLine();
            String consultaSQL = "SELECT * FROM medico WHERE cpf_paciente = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            preparedStatement.setString(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                String nomeMedico = resultado.getString("nome_paciente");
                int idPaciente = resultado.getInt("id_paciente");
                String nascimentoPaciente = resultado.getString("nascimento_paciente");
                String cpfPaciente = resultado.getString("cpf_paciente");
                String generoPaciente = resultado.getString("genero_paciente");

                System.out.println("ID: " + idPaciente + ", Nome: " + nomeMedico + ", Nascimento: " + nascimentoPaciente + ", CPF: " + cpfPaciente + ", Genero: " + generoPaciente);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar paciente: " + e.getMessage());
        }
    }

}
