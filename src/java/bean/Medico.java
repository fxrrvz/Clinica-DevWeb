/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Ferraz-PC
 */
public class Medico extends Usuario{
    
    public Medico(String _nome, String _cpf, String _senha) {
        super(_nome, _cpf, _senha);
    }
    
    public int crm;
    public String estadocrm;
    public String autorizado;
    public int idespecialidade;

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getEstadoCrm() {
        return estadocrm;
    }

    public void setEstadoCrm(String estado_crm) {
        this.estadocrm = estado_crm;
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

    public void setIdEspecialidade(int id_especialidade) {
        this.idespecialidade = id_especialidade;
    }
    
    
}
