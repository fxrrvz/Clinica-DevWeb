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
public class Medico{
    
    public int id;
    public String nome;
    public String cpf;
    public String senha;
    public int crm;
    public String estadocrm;
    public String autorizado;
    public int idespecialidade;

    public Medico(String nome, String cpf, String senha, int crm, String estadocrm, String autorizado, int idespecialidade) {
        this.id = 0;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.autorizado = autorizado;
        this.idespecialidade = idespecialidade;
    }

    public Medico() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.crm = 0;
        this.estadocrm = "";
        this.autorizado = "";
        this.idespecialidade = 0;
    }

    
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getEstadoCrm() {
        return estadocrm;
    }

    public void setEstadoCrm(String estadocrm) {
        this.estadocrm = estadocrm;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public int getIdEspecialidade() {
        return idespecialidade;
    }

    public void setIdEspecialidade(int idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    
}
