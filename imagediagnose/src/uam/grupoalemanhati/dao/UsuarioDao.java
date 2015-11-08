package uam.grupoalemanhati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uam.grupoalemanhati.infra.ConnectionFactory;
import uam.grupoalemanhati.model.Usuario;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe UsuarioDao - Operações com o Banco de Dados
 */
public class UsuarioDao {
    
    //conexão com o Banco
    private final Connection connection;
    
    //construtor
    public UsuarioDao() throws SQLException {
        this.connection = new ConnectionFactory().getConexao();
    }
    
    //inserir
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIOS (LOGIN, SENHA, NOME, TIPO) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }
    
    //atualizar
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIOS SET LOGIN = ?, SENHA = ?, NOME=?, TIPO=? WHERE ID=?";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setInt(5, usuario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
    }   
    
    //login
    public int login(Usuario usuario) throws SQLException {
        //Se retornar -1 é pq não consegui logar
        int id     = -1;

        String sql = "SELECT * FROM USUARIOS WHERE LOGIN = ? AND SENHA = ?";
                 try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt("ID");
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
        return id;
    }
    
    //getusuario
    public Usuario getUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                usuario.setId(rs.getInt("ID"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setTipo(rs.getString("TIPO"));
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();
        return usuario;

    }

}
