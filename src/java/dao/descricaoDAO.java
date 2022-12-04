/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import aplicacao.Descricao;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */

////////////////////////////////////////////////////////////////////////////////////////////
/////////// descricaoDAO é usada pelas classes especialidade/tipoexame/tipoplano ///////////
////////////////////////////////////////////////////////////////////////////////////////////

public class descricaoDAO {
    
    public void create(Descricao desc, String tabela){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO "+ tabela + "(descricao) VALUES(?)");
            stmt.setString(1, desc.getDescricao());
            
            stmt.executeUpdate();
            
            System.out.print("Salvo com sucesso!");
            
        } catch (SQLException ex) {
            System.out.print(ex);
            System.out.print("Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Descricao> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Descricao> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Consulta");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Descricao desc = new Descricao();
                desc.setDescricao(rs.getString("descricao"));
                lista.add(desc);                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(descricaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}


/*public ConsultaDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/