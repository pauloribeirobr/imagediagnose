package uam.grupoalemanhati.infra;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Cria as tabelas do Projeto
 */
public class PopulaDados {
    
    //conexão com o Banco
    public static Connection connection;

    public static void main(String args[]) throws SQLException, FileNotFoundException, IOException {
        String sql;
        PreparedStatement stmt;
        PopulaDados.connection = new ConnectionFactory().getConexao();
       

        /*
        USUARIOS
        */
        //Apaga a tabela usuarios
        sql = "DROP TABLE IF EXISTS USUARIOS";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela USUARIOS apagada");            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Cria a tabela USUARIOS
        sql = "CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "LOGIN VARCHAR(30), SENHA VARCHAR(44), "
                + "NOME VARCHAR(40), TIPO VARCHAR(7));"; 
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela USUARIOS criada!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Insere os usuários
        //Tipo = 0 - usuário comum -- 1 - medico
        sql = "INSERT INTO USUARIOS (ID, LOGIN, SENHA, NOME, TIPO) VALUES (1, 'COMUM', '123123', 'USUARIO COMUM', 'USUARIO')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Usuario COMUM inserido!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "INSERT INTO USUARIOS (ID, LOGIN, SENHA, NOME, TIPO) VALUES (2, 'MEDICO', '123123', 'USUARIO MEDICO', 'MEDICO')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Usuario MEDICO inserido!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        /*
        MEDICOS
        */
        //Apaga a tabela MEDICOS
        sql = "DROP TABLE IF EXISTS MEDICOS";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela MEDICOS apagada");            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Cria a tabela MEDICOS
        sql = "CREATE TABLE MEDICOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(80));"; 
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela MEDICOS criada!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Insere os MEDICOS
        sql = "INSERT INTO MEDICOS (ID, NOME) VALUES (1, 'Drauzio Varela')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Medico Drauzio Varela inserido!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "INSERT INTO MEDICOS (ID, NOME) VALUES (2, 'Ivo Pitangui')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Medico Ivo Pitangui inserido!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
        
        /*
        PACIENTES
        */
        //Apaga a tabela PACIENTES
        sql = "DROP TABLE IF EXISTS PACIENTES";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela MEDICOS apagada");            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Cria a tabela PACIENTES
        sql = "CREATE TABLE PACIENTES (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME VARCHAR(80), "
                + "NASCIMENTO DATE, IDADE INTEGER, PESO INTEGER, ALTURA INTEGER, SEXO VARCHAR(1));"; 
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela MEDICOS criada!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Insere Paciente
        sql = "INSERT INTO PACIENTES (ID, NOME, NASCIMENTO, IDADE, PESO, ALTURA, SEXO) "
                +" VALUES (1, 'Paulo Ribeiro', '24/01/1978', 37, 102, 188, 'M')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Paciente Paulo Ribeiro inserido!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        /*
        ANAMNESES
        */
        //Apaga a tabela ANAMNESES
        sql = "DROP TABLE IF EXISTS ANAMNESES";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela ANAMNESES apagada");            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Cria a tabela ANAMNESES
        sql = "CREATE TABLE ANAMNESES (ID INTEGER PRIMARY KEY AUTOINCREMENT, IDUSUARIO INTEGER, IDMEDICO INTEGER, "
                + "IDPACIENTE INT, DATA DATE, QUEIXA TEXT, DIAGNOSTICO TEXT, PRESCRICAO TEXT);"; 
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela ANAMNESES criada!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Insere uma ANAMNESE
        sql = "INSERT INTO ANAMNESES (ID, IDUSUARIO, IDMEDICO, IDPACIENTE, DATA, QUEIXA, DIAGNOSTICO, PRESCRICAO) "
                +"VALUES (1, 2, 1, 1, '06/11/2015', 'Paciente se queixa de dor nas costas', "
                +"'Inflamação originada por queda', 'Aspirina e Repouso')";
                try {
        stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Anamnese 1 inserida!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
                
        /*
        ANAMNESES_IMAGENS
        */
                
        //Apaga a tabela
        sql = "DROP TABLE IF EXISTS ANAMNESES_IMAGENS";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela ANAMNESES_IMAGENS apagada");            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Cria a tabela
        sql = "CREATE TABLE ANAMNESES_IMAGENS (ID INTEGER PRIMARY KEY AUTOINCREMENT, IDANAMNESE INTEGER ,"
                + "DESCRICAO VARCHAR, IMAGEM BLOB);"; 
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela ANAMNESES_IMAGENS criada!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }       
        
        /*
        //Insere uma imagem        
        */

        long imagemTamanho = 0; 
        byte[] arquivoBytes = null;
        File imagemArquivo = new File("imagem01.png");
        FileInputStream arquivoStream = new FileInputStream(imagemArquivo);

        imagemTamanho = imagemArquivo.length();

        if(imagemTamanho > 0) {
            arquivoBytes = new byte[(int)imagemTamanho];
            arquivoStream.read(arquivoBytes);
        }
        
        sql = "INSERT INTO ANAMNESES_IMAGENS (IDANAMNESE, DESCRICAO, IMAGEM) VALUES (?, ?, ?)";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, 1);
        stmt.setString(2, "Imagem de Teste");
        stmt.setBytes(3, arquivoBytes);

        try {
            stmt.execute();
            stmt.close();
            System.out.println("Imagem 1 da Anamnese 1 inserida!");  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Mostra o arquivo só pra teste
        Image imagem = null;
        imagem=Toolkit.getDefaultToolkit().createImage(arquivoBytes);
        ImageIcon ico=new ImageIcon(imagem) ;  
        

    }
}
