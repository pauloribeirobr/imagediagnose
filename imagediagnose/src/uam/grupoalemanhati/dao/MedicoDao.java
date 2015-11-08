package uam.grupoalemanhati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uam.grupoalemanhati.infra.ConnectionFactory;
import uam.grupoalemanhati.model.Medico;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe MedicoDao - Operações com o Banco de Dados
 */
public class MedicoDao {
    
    //conexão com o Banco
    private final Connection connection;
    
    //construtor
    public MedicoDao() throws SQLException {
        this.connection = new ConnectionFactory().getConexao();
    }
    
    //retorna um registro
    public Medico getMedico(int id) throws SQLException {
        String sql = "SELECT * FROM MEDICOS WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs;
        rs = stmt.executeQuery();
        Medico medico = new Medico();
        if(rs.next()) {
            medico.setId(rs.getInt("ID"));
            medico.setNome(rs.getString("NOME"));
        }
        rs.close();
        stmt.close();
        connection.close();
        
        return medico;
    }
    
    //inserir
    public void inserir(Medico medico) throws SQLException {
             
        String sql = "INSERT INTO MEDICOS (NOME) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }
    
    //atualizar
    public void atualizar(Medico medico) throws SQLException {
             
        String sql = "UPDATE MEDICOS SET NOME = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setInt(2, medico.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }  
    
    //listar
        public List<Medico> lista() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM MEDICOS ORDER BY NOME");
            ResultSet rs = stmt.executeQuery();
            List<Medico> medicos = new ArrayList<>();
            
            while(rs.next()) {
                //cria o objeto medico
                Medico medico = new Medico();
                medico.setId(rs.getInt("ID"));
                medico.setNome(rs.getString("NOME"));

                //adiciona a lista
                medicos.add(medico);
            }
            rs.close();
            stmt.close();
            connection.close();
            
            return medicos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
