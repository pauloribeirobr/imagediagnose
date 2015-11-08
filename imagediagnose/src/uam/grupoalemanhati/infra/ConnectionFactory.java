package uam.grupoalemanhati.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe ConnectionFactory - ConnectionFactory ao Banco de Dados
 */
public class ConnectionFactory {
    
    String stringDeConexao = "jdbc:sqlite:db/imd.sqlite";

    public Connection getConexao() throws SQLException {

        return DriverManager.getConnection(stringDeConexao);
    }
 
}
