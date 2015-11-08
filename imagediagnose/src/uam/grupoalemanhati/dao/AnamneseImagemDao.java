package uam.grupoalemanhati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uam.grupoalemanhati.infra.ConnectionFactory;
import uam.grupoalemanhati.model.AnamneseImagem;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe AnamneseImagemDao - Operações com o Banco de Dados
 */
public class AnamneseImagemDao {
    
    //conexão com o Banco
    private final Connection connection;
    
    //construtor
    public AnamneseImagemDao() throws SQLException {
        this.connection = new ConnectionFactory().getConexao();
    }
    
    //retorna 1 registro
    public AnamneseImagem getAnamneseImagem(int id) throws SQLException {
        String sql = "SELECT * FROM ANAMNESES_IMAGENS WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        
        ResultSet rs;
        rs = stmt.executeQuery();
        
        AnamneseImagem anamneseImagem = new AnamneseImagem();
        
        if(rs.next()) {
            anamneseImagem.setId(rs.getInt("ID"));
            anamneseImagem.setIdAnamnese(rs.getInt("IDANAMNESE"));
            anamneseImagem.setDescricao(rs.getString("DESCRICAO"));
            anamneseImagem.setImagem(rs.getBytes("IMAGEM"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return anamneseImagem;
    }
    
    //inserir
    public void inserir(AnamneseImagem anamneseImagem) {
        String sql = "INSERT INTO ANAMNESES_IMAGENS (IDANAMNESE, DESCRICAO, IMAGEM) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, anamneseImagem.getIdAnamnese());
            stmt.setString(2, anamneseImagem.getDescricao());
            stmt.setBytes(3, anamneseImagem.getImagem());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //apagar
    public void apagar(int id) throws SQLException {
        String sql = "DELETE FROM ANAMNESES_IMAGENS WHERE ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
        connection.close();

    }
    
    //listar (sem as imagens) e somente as imagens da Anamnese
    public List<AnamneseImagem> lista(int idAnamnese) throws SQLException {
        
        List<AnamneseImagem> anamnesesImagens = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ANAMNESES_IMAGENS WHERE IDANAMNESE = ?");
        stmt.setInt(1, idAnamnese);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
            
            AnamneseImagem anamneseImagem = new AnamneseImagem();
            anamneseImagem.setId(rs.getInt("ID"));
            anamneseImagem.setIdAnamnese(rs.getInt("IDANAMNESE"));
            anamneseImagem.setDescricao(rs.getString("DESCRICAO"));
            anamneseImagem.setImagem(rs.getBytes("IMAGEM"));

            //adiciona a lista
            anamnesesImagens.add(anamneseImagem);
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return anamnesesImagens;
    }
    
}
