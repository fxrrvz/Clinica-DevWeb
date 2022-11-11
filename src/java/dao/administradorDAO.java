
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
import bean.Administrador;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class administradorDAO {
    
    public void create(Administrador adm){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO administrador(nome, cpf, senha)" +
                                        " VALUES(?,?,?,?)");
            stmt.setString(2, adm.getNome());
            stmt.setString(3, adm.getCpf());
            stmt.setString(4, adm.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Administrador> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Administrador> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administrador");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Administrador administrador = new Administrador(rs.getString("nome"),
                                                                rs.getString("cpf"),
                                                                rs.getString("senha"));
                lista.add(administrador);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(administradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}
