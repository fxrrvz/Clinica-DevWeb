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
public class Paciente{

    public int id;
    public String nome;
    public String cpf;
    public String senha;
    public String autorizado;
    public int idtipoplano;

    public Paciente(int id, String nome, String cpf, String senha, String autorizado, int idtipoplano) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.autorizado = autorizado;
        this.idtipoplano = idtipoplano;
    }

    public Paciente(String nome, String cpf, String senha, int idtipoplano) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.autorizado = "N";
        this.idtipoplano = idtipoplano;
    }

    
    public Paciente() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.autorizado = "";
        this.idtipoplano = 0;
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

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public int getIdTipoPlano() {
        return idtipoplano;
    }

    public void setIdTipoPlano(int idtipoplano) {
        this.idtipoplano = idtipoplano;
    }

    
}
