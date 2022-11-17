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
import aplicacao.Usuario;
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
            stmt = con.prepareStatement("INSERT INTO (?) VALUES (?,?,?,?)");
            stmt.setString(1, banco);
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getCpf());
            stmt.setString(4, user.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Usuario> read(String banco){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        List<Usuario> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ?");
            stmt.setString(1, banco);
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
    
    public Usuario logar(Usuario usuario, String banco) throws Exception {
        System.out.print("banco "+ banco + "\n");
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        ResultSet resultado;
        try {
            stmt = con.prepareStatement(String.format("SELECT * FROM %s WHERE cpf=? and senha=?;", banco));
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getSenha());
            
            resultado = stmt.executeQuery();
            Usuario usuarioObtido = new Usuario();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.setId(Integer.parseInt(resultado.getString("id")));
                    usuarioObtido.setNome(resultado.getString("nome"));
                    usuarioObtido.setCpf(resultado.getString("cpf"));
                    usuarioObtido.setSenha(resultado.getString("senha"));
                }
            }
             return usuarioObtido;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }    
}
