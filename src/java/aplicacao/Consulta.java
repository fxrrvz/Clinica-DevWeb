/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

/**
 *
 * @author Ferraz-PC
 */
public class Consulta{
    
    public int id;
    public String data;
    public String descricao;
    public String realizada;
    public int idmedico;
    public int idpaciente;

    public Consulta(String data, String descricao, int idmedico, int idpaciente) {
        this.id = 0;
        this.data = data;
        this.descricao = descricao;
        this.realizada = "N";
        this.idmedico = idmedico;
        this.idpaciente = idpaciente;
    }

    public Consulta(int id, String data, String descricao, String realizada, int idmedico, int idpaciente) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.realizada = realizada;
        this.idmedico = idmedico;
        this.idpaciente = idpaciente;
    }

    
    public Consulta() {
        this.id = 0;
        this.data = "";
        this.descricao = "";
        this.realizada = "N";
        this.idmedico = 0;
        this.idpaciente = 0;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }

    public int getIdMedico() {
        return idmedico;
    }

    public void setIdMedico(int idmedico) {
        this.idmedico = idmedico;
    }

    public int getIdPaciente() {
        return idpaciente;
    }

    public void setIdPaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }
    
    
}
