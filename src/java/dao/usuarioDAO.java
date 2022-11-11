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
import bean.classes_usuario.Usuario;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class usuarioDAO {
    
    public void create(Usuario user, String banco){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO "+ banco +" VALUES(?,?,?)");
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getCpf());
            stmt.setString(3, user.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Usuario> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Usuario usuario = new Usuario(rs.getInt("id"),
                                              rs.getString("nome"),
                                              rs.getString("cpf"),
                                              rs.getString("senha"));
                lista.add(usuario);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}
