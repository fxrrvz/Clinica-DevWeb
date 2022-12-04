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
public class Descricao {
    private String descricao;

    public Descricao(String descricao) {
        this.descricao = descricao;
    }

    public Descricao() {
        this.descricao = "";
    }
    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
