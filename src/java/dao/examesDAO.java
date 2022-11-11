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
import bean.Exames;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class examesDAO {
    
    public void create(Exames exam){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO exames(idconsulta, idtipoexame) VALUES(?,?)");
            stmt.setInt(1, exam.getIdConsulta());
            stmt.setInt(2, exam.getIdTipoExame());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Exames> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Exames> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Consulta");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Exames exames = new Exames();
                exames.setId(rs.getInt("id"));
                exames.setIdConsulta(rs.getInt("idconsulta"));
                exames.setIdTipoExame(rs.getInt("idtipoexame"));
                lista.add(exames);                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(consultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}


/*public ConsultaDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/