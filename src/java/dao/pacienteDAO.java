
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
import bean.classes_usuario.Paciente;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class pacienteDAO {
    
    public void create(Paciente pc){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO paciente(nome, cpf, senha, autorizado, idtipoplano)"+
                                        " VALUES(?,?,?,?,?)");
            stmt.setString(1, pc.getNome());
            stmt.setString(2, pc.getCpf());
            stmt.setString(3, pc.getSenha());
            stmt.setString(4, pc.getAutorizado());
            stmt.setInt(5, pc.getIdTipoPlano());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Paciente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Paciente");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Paciente paciente = new Paciente(rs.getInt("id"),
                                                 rs.getString("nome"),
                                                 rs.getString("cpf"),
                                                 rs.getString("senha"));
                paciente.setAutorizado(rs.getString("autorizado"));
                paciente.setIdTipoPlano(rs.getInt("idtipoplano"));
                lista.add(paciente);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(pacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}
