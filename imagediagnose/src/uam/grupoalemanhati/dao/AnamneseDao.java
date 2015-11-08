package uam.grupoalemanhati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import uam.grupoalemanhati.infra.ConnectionFactory;
import uam.grupoalemanhati.model.Anamnese;
import uam.grupoalemanhati.model.Medico;
import uam.grupoalemanhati.model.Paciente;
import uam.grupoalemanhati.model.Usuario;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe AnamneseDao - Operações com o Banco de Dados
 */
public class AnamneseDao {
    
    //conexão com o Banco
    private final Connection connection;
    
    //construtor
    public AnamneseDao() throws SQLException {
        this.connection = new ConnectionFactory().getConexao();
    }
    
    //retorna um registro
    public Anamnese getAnamnese(int id) throws SQLException, ParseException {
        String sql = "SELECT * FROM ANAMNESES WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        
        ResultSet rs;
        rs = stmt.executeQuery();
        
        Anamnese anamnese = new Anamnese();
        //Objetos de Apoio
        Usuario usuario = new Usuario();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        
        if(rs.next()) {
            anamnese.setId(rs.getInt("ID"));
            //Associa as classes aos IDs encontrados no registro
            usuario = new UsuarioDao().getUsuario(rs.getInt("IDUSUARIO"));
            anamnese.setUsuario(usuario);
            medico = new MedicoDao().getMedico(rs.getInt("IDMEDICO"));
            anamnese.setMedico(medico);
            paciente = new PacienteDao().getPaciente(rs.getInt("IDPACIENTE"));
            anamnese.setPaciente(paciente);
            //Converte a String do Banco para Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
            anamnese.setData(sdf.parse(rs.getString("DATA")));
            
            anamnese.setQueixa(rs.getString("QUEIXA"));
            anamnese.setDiagnostico(rs.getString("DIAGNOSTICO"));
            anamnese.setPrescricao(rs.getString("PRESCRICAO"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return anamnese;
    }
    
    //inserir
    public void inserir(Anamnese anamnese) {
        String sql = "INSERT INTO ANAMNESES (IDUSUARIO, IDMEDICO, IDPACIENTE, DATA, QUEIXA, DIAGNOSTICO, PRESCRICAO) "
                +"VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, anamnese.getUsuario().getId());
            stmt.setInt(2, anamnese.getMedico().getId());
            stmt.setInt(3, anamnese.getPaciente().getId());
            //Converte Date para o formato dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            stmt.setString(4, sdf.format(anamnese.getData()));
            stmt.setString(5, anamnese.getQueixa());
            stmt.setString(6, anamnese.getDiagnostico());
            stmt.setString(7, anamnese.getPrescricao());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //atualizar
    public void atualizar(Anamnese anamnese) {
        String sql = "UPDATE ANAMNESES SET IDUSUARIO = ?, IDMEDICO = ?, IDPACIENTE = ?, DATA = ?, "
                + "QUEIXA = ?, DIAGNOSTICO = ?, PRESCRICAO = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, anamnese.getUsuario().getId());
            stmt.setInt(2, anamnese.getMedico().getId());
            stmt.setInt(3, anamnese.getPaciente().getId());
            //Converte Date para o formato dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            stmt.setString(4, sdf.format(anamnese.getData()));
            stmt.setString(5, anamnese.getQueixa());
            stmt.setString(6, anamnese.getDiagnostico());
            stmt.setString(7, anamnese.getPrescricao());
            stmt.setInt(8, anamnese.getId());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //listar
    public List<Anamnese> lista() throws ParseException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ANAMNESES ORDER BY ID DESC");
            ResultSet rs = stmt.executeQuery();
            List<Anamnese> anamneses = new ArrayList<>();
            
            while(rs.next()) {
                //cria o objeto Anamnese
                Anamnese anamnese = new Anamnese();
                //cria os objetos de apoio
                Usuario usuario = new Usuario();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                
                anamnese.setId(rs.getInt("ID"));
                //Associa as classes aos IDs encontrados no registro
                usuario = new UsuarioDao().getUsuario(rs.getInt("IDUSUARIO"));
                anamnese.setUsuario(usuario);
                medico = new MedicoDao().getMedico(rs.getInt("IDMEDICO"));
                anamnese.setMedico(medico);
                paciente = new PacienteDao().getPaciente(rs.getInt("IDPACIENTE"));
                anamnese.setPaciente(paciente);
                //Converte a String do Banco para Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
                anamnese.setData(sdf.parse(rs.getString("DATA")));
            
                anamnese.setQueixa(rs.getString("QUEIXA"));
                anamnese.setDiagnostico(rs.getString("DIAGNOSTICO"));
                anamnese.setPrescricao(rs.getString("PRESCRICAO"));
                
                //adiciona a lista
                anamneses.add(anamnese);
            }
            rs.close();
            stmt.close();
            connection.close();
            
            return anamneses;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //lista Anamneses de um paciente especídigo
    public List<Anamnese> listaPaciente(Paciente idPaciente) throws ParseException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ANAMNESES WHERE IDPACIENTE = ?");
            stmt.setInt(1, idPaciente.getId());
            ResultSet rs = stmt.executeQuery();
            List<Anamnese> anamneses = new ArrayList<>();
            
            while(rs.next()) {
                //cria o objeto Anamnese
                Anamnese anamnese = new Anamnese();
                //cria os objetos de apoio
                Usuario usuario = new Usuario();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                
                anamnese.setId(rs.getInt("ID"));
                //Associa as classes aos IDs encontrados no registro
                usuario = new UsuarioDao().getUsuario(rs.getInt("IDUSUARIO"));
                anamnese.setUsuario(usuario);
                medico = new MedicoDao().getMedico(rs.getInt("IDMEDICO"));
                anamnese.setMedico(medico);
                paciente = new PacienteDao().getPaciente(rs.getInt("IDPACIENTE"));
                anamnese.setPaciente(paciente);
                //Converte a String do Banco para Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
                anamnese.setData(sdf.parse(rs.getString("DATA")));
            
                anamnese.setQueixa(rs.getString("QUEIXA"));
                anamnese.setDiagnostico(rs.getString("DIAGNOSTICO"));
                anamnese.setPrescricao(rs.getString("PRESCRICAO"));
                
                //adiciona a lista
                anamneses.add(anamnese);
            }
            rs.close();
            stmt.close();
            connection.close();
            
            return anamneses;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
