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
import bean.Consulta;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class consultaDAO {
    
    public void create(Consulta consulta){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO consulta(data, descricao, realizada, idmedico, idpaciente) VALUES(?,?,?,?,?)");
            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getDescricao());
            stmt.setString(3, consulta.getRealizada());
            stmt.setInt(4, consulta.getIdMedico());
            stmt.setInt(5, consulta.getIdPaciente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Consulta> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Consulta");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getString("data"));
                consulta.setDescricao(rs.getString("descricao"));
                consulta.setRealizada(rs.getString("realizada"));
                consulta.setIdMedico(rs.getInt("idmedico"));
                consulta.setIdPaciente(rs.getInt("idpaciente"));
                lista.add(consulta);                
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