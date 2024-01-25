package org.example.Classes;

//import org.example.Conexao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.ZoneId;

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

    public void addConsulta (Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o CPF do paciente");
            String cpfPaciente = scanner.nextLine();
            System.out.println("Digite o CRM do medico");
            String crmMedico = scanner.nextLine();
            System.out.println("Digite a data da consulta");
            String data = scanner.nextLine();
            System.out.println("Digite o horario da consulta");
            String horario = scanner.nextLine();
            System.out.println("Digite o motivo da consulta");
            String motivo = scanner.nextLine();
            System.out.println("Digite o diagnostico da consulta");
            String diagnostico = scanner.nextLine();
            System.out.println("Digite a prescrição da consulta");
            String prescricao = scanner.nextLine();
            System.out.println("Digite a observação da consulta");
            String observacao = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataUtil = formato.parse(data);
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalDateTime userDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(horario));

            Timestamp timestamp = Timestamp.valueOf(userDateTime.atZone(ZoneId.of("America/Maceio")).toLocalDateTime());

            String sql_medico = "SELECT id_medico FROM medico WHERE crm_medico = ?";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql_medico);
            preparedStatement1.setString(1, crmMedico);
            ResultSet resultado1 = preparedStatement1.executeQuery();
            int idMedico = resultado1.getInt("id_medico");

            String sql_paciente = "SELECT id_paciente FROM paciente WHERE cpf_paciente = ?";
            PreparedStatement preparedStatement2 = conexao.prepareStatement(sql_paciente);
            preparedStatement2.setString(1, cpfPaciente);
            ResultSet resultado2 = preparedStatement2.executeQuery();
            int idPaciente = resultado2.getInt("id_paciente");


            String sql = "INSERT INTO consulta (paciente_id, medico_id, data_consulta, hora_consulta, motivo_consulta, diagnostico, prescricao, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement3 = conexao.prepareStatement(sql);
            preparedStatement3.setInt(1, idPaciente);
            preparedStatement3.setInt(2, idMedico);
            preparedStatement3.setDate(3, dataSql);
            preparedStatement3.setTimestamp(4, timestamp);
            preparedStatement3.setString(5, motivo);
            preparedStatement3.setString(6, diagnostico);
            preparedStatement3.setString(7, prescricao);
            preparedStatement3.setString(8, observacao);
            preparedStatement3.execute();
            System.out.println("Consulta adicionada com sucesso");

        }
        catch (SQLException | RuntimeException | ParseException e) {
            System.err.println("Erro na conexão com o Banco de Dados para adicionar consulta: " + e.getMessage());
        }
    }

    // Remover da tables
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

    public void removePaciente (Connection conexao){
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

    public void removeConsulta (Connection conexao){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o ID da consulta");
            int id = scanner.nextInt();

            String sql = "DELETE FROM consulta WHERE id_consulta = ?";
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
            preparedStatement1.setInt(1, id);
            preparedStatement1.execute();
            System.out.println("Consulta removida com sucesso");
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para remover consulta: " + e.getMessage());
        }
    }

    // Printar medico

    public void printConsulta(Connection conexao) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todas as consultas");
        System.out.println("Digite 2 para printar uma consulta especifica");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                printAllConsulta(conexao);
                break;
            case "2":
                printOneConsulta(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\nTente novamente");
                break;
        }
    }

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

    public void printPaciente(Connection conexao) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        System.out.println("Digite 1 para printar todos os pacientes");
        System.out.println("Digite 2 para printar um pacientes especifico");
        System.out.println("Digite 3 para sair");
        opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                printAllPaciente(conexao);
                break;
            case "2":
                printOnePaciente(conexao);
                break;
            case "3":
                break;
            default:
                System.err.println("Opcao invalida\n");
                break;
        }
    }

    public void printAllConsulta(Connection conexao) {
        try {
            String consultaSQL = "SELECT consulta.id_consulta, paciente.cpf_paciente, medico.crm_medico, consulta.data_consulta, consulta.hora_consulta, consulta.motivo_consulta, consulta.diagnostico, consulta.prescricao, consulta.observacoes FROM consulta JOIN paciente ON consulta.paciente_id = paciente.id_paciente JOIN medico ON consulta.medico_id = medico.id_medico;";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idConsulta = resultado.getInt("id_consulta");
                String idPaciente = resultado.getString("cpf_paciente");
                String idMedico = resultado.getString("crm_medico");
                String dataConsulta = resultado.getString("data_consulta");
                String horaConsulta = resultado.getString("hora_consulta");
                String motivoConsulta = resultado.getString("motivo_consulta");
                String diagnostico = resultado.getString("diagnostico");
                String prescricao = resultado.getString("prescricao");
                String observacoes = resultado.getString("observacoes");

                System.out.println("ID: " + idConsulta + ", ID Paciente: " + idPaciente + ", ID Medico: " + idMedico + ", Data: " + dataConsulta + ", Hora: " + horaConsulta + ", Motivo: " + motivoConsulta + ", Diagnostico: " + diagnostico + ", Prescricao: " + prescricao + ", Observacoes: " + observacoes);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar consulta: " + e.getMessage());
        }
    }

    public void printOneConsulta(Connection conexao) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o ID da consulta");
            int id = scanner.nextInt();
            String consultaSQL = "SELECT consulta.id_consulta, paciente.cpf_paciente, medico.crm_medico, consulta.data_consulta, consulta.hora_consulta, consulta.motivo_consulta, consulta.diagnostico, consulta.prescricao, consulta.observacoes FROM consulta JOIN paciente ON consulta.paciente_id = paciente.id_paciente JOIN medico ON consulta.medico_id = medico.id_medico where id_consulta = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idConsulta = resultado.getInt("id_consulta");
                String idPaciente = resultado.getString("cpf_paciente");
                String idMedico = resultado.getString("crm_medico");
                String dataConsulta = resultado.getString("data_consulta");
                String horaConsulta = resultado.getString("hora_consulta");
                String motivoConsulta = resultado.getString("motivo_consulta");
                String diagnostico = resultado.getString("diagnostico");
                String prescricao = resultado.getString("prescricao");
                String observacoes = resultado.getString("observacoes");

                System.out.println("ID: " + idConsulta + ", ID Paciente: " + idPaciente + ", ID Medico: " + idMedico + ", Data: " + dataConsulta + ", Hora: " + horaConsulta + ", Motivo: " + motivoConsulta + ", Diagnostico: " + diagnostico + ", Prescricao: " + prescricao + ", Observacoes: " + observacoes);
            }
        }
        catch (SQLException e) {
            System.err.println("Erro na conexão com o Banco de Dados para printar consulta: " + e.getMessage());
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

    public void printAllPaciente(Connection conexao) {
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

    public void printOnePaciente(Connection conexao) {
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
