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
public class Paciente extends Usuario{

    public Paciente(String _nome, String _cpf, String _senha) {
        super(_nome, _cpf, _senha);
    }
    
    public String autorizado;
    public int idtipoplano;

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
