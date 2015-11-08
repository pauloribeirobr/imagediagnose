package uam.grupoalemanhati.model;
import java.util.Date;

/*
 * UAM - Grupo Alemanha TI - Projeto iMage Diagnose - 5 Semestre - 2015
 * @author Paulo Ribeiro
 * 
 * @version 1.0
 * 
 * Classe Anamnese
 */
public class Anamnese {
    /*
    Atributos
    */
    private int         id;
    private Usuario     usuario;
    private Medico      medico;
    private Paciente    paciente;
    private Date        data;
    private String      queixa;
    private String      diagnostico;
    private String      prescricao;
    
    /*
    Gets e Sets
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }
    
}
