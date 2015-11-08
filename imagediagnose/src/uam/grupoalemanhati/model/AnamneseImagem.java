package uam.grupoalemanhati.model;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe AnamneseImagem
 */
public class AnamneseImagem {
    
    private int id;
    private int idAnamnese;
    private String descricao;
    private byte [] imagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnamnese() {
        return idAnamnese;
    }

    public void setIdAnamnese(int idAnamnese) {
        this.idAnamnese = idAnamnese;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte [] getImagem() {
        return imagem;
    }

    public void setImagem(byte [] imagem) {
        this.imagem = imagem;
    }

}
