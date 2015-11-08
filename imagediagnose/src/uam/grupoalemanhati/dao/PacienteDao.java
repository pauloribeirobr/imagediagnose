package uam.grupoalemanhati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uam.grupoalemanhati.infra.ConnectionFactory;
import uam.grupoalemanhati.model.Paciente;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe PacienteDao - Operações com o Banco de Dados
 */
public class PacienteDao {
    
    //conexão com o Banco
    private final Connection connection;
    
    //construtor
    public PacienteDao() throws SQLException {
        this.connection = new ConnectionFactory().getConexao();
    }
    
    //retorna um registro
    public Paciente getPaciente(int id) throws SQLException {
        String sql = "SELECT * FROM PACIENTES WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs;
        rs = stmt.executeQuery();
        Paciente paciente = new Paciente();
        if(rs.next()) {
            paciente.setId(rs.getInt("ID"));
            paciente.setNome(rs.getString("NOME"));
            paciente.setDataDeNascimento(rs.getString("NASCIMENTO"));
            paciente.setIdade(rs.getInt("IDADE"));
            paciente.setAltura(rs.getInt("ALTURA"));
            paciente.setPeso(rs.getInt("PESO"));
            paciente.setSexo(rs.getString("SEXO"));
        }
        rs.close();
        stmt.close();
        connection.close();
        
        return paciente;
    }
    
    //Adicionar Paciente
    public void inserir(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO PACIENTES (NOME, NASCIMENTO, IDADE, ALTURA, PESO, SEXO) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getDataDeNascimento());
                stmt.setInt(3, paciente.getIdade());
                stmt.setInt(4, paciente.getAltura());
                stmt.setInt(5, paciente.getPeso());
                stmt.setString(6, paciente.getSexo());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }
    
    //Atualizar
    public void atualizar(Paciente paciente) throws SQLException {
        String sql = "UPDATE PACIENTES SET NOME = ?, NASCIMENTO=?, IDADE=?, ALTURA=?, PESO=?, "
                + "SEXO = ? WHERE ID = ?";
                try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getDataDeNascimento());
            stmt.setInt(3, paciente.getIdade());
            stmt.setInt(4, paciente.getAltura());
            stmt.setInt(5, paciente.getPeso());
            stmt.setString(6, paciente.getSexo());
            stmt.setInt(7, paciente.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }
    
 
    //listar
    public List<Paciente> lista() {
        try {
            //Instancia a Classe Calendário

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM PACIENTES ORDER BY NOME");
            ResultSet rs = stmt.executeQuery();
            List<Paciente> pacientes = new ArrayList<>();
            
            while(rs.next()) {
                //cria o objeto paciente
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("ID"));
                paciente.setNome(rs.getString("NOME"));
                paciente.setDataDeNascimento(rs.getString("NASCIMENTO"));
                paciente.setIdade(rs.getInt("IDADE"));
                paciente.setAltura(rs.getInt("ALTURA"));
                paciente.setPeso(rs.getInt("PESO"));
                paciente.setSexo(rs.getString("SEXO"));

                //adiciona a lista
                pacientes.add(paciente);
            }
            rs.close();
            stmt.close();
            connection.close();
            
            return pacientes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
