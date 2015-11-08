package uam.grupoalemanhati.model;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe Medico
 */
public class Medico {
    /*
    Definicao dos atributos
    */
    private int     id;
    private String  nome;
    
    /*
    Gets e Sets
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Método usado para mostrar somente o nome do médico em qualquer ComboBox
    @Override
    public String toString() {
        return getNome();
    }
}
