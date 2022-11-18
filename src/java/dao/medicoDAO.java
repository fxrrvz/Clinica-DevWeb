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
import aplicacao.Medico;
import connection.ConnectionFactory;
/**
 *
 * @author Ferraz-PC
 */
public class medicoDAO {
    
    public void create(Medico med){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO medico(nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade)" +
                                        " VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, med.getNome());
            stmt.setString(2, med.getCpf());
            stmt.setString(3, med.getSenha());
            stmt.setInt   (4, med.getCrm());
            stmt.setString(5, med.getEstadoCrm());
            stmt.setString(6, med.getAutorizado());
            stmt.setInt   (7, med.getIdEspecialidade());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public List<Medico> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        List<Medico> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM medico");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setSenha(rs.getString("senha"));
                medico.setCrm(rs.getInt("crm"));
                medico.setEstadoCrm(rs.getString("estadocrm"));
                medico.setAutorizado(rs.getString("autorizado"));
                medico.setIdEspecialidade(rs.getInt("idespecialidade"));
                lista.add(medico);                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(medicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    
}


/*public medicoDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/