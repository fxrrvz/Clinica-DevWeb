
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
import aplicacao.Administrador;
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
            stmt.setString(1, adm.getNome());
            stmt.setString(2, adm.getCpf());
            stmt.setString(3, adm.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public ArrayList<Administrador> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Administrador> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administrador");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Administrador administrador = new Administrador(rs.getInt("id"),
                                                                rs.getString("nome"),
                                                                rs.getString("cpf"),
                                                                rs.getString("senha"));
                lista.add(administrador);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(administradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

        return lista;
        
    }
    
    public Administrador getAdministrador(int id) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            Administrador adm = new Administrador();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuarios WHERE ID = ? ");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    adm.setId(Integer.parseInt(resultado.getString("ID")));
                    adm.setNome(resultado.getString("NOME"));
                    adm.setCpf(resultado.getString("CPF"));
                    adm.setSenha(resultado.getString("SENHA"));
                }
            }
            return adm;
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public Administrador logar(Administrador adm) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM administrador WHERE cpf=? and senha =? LIMIT 1");
            stmt.setString(1, adm.getCpf());
            stmt.setString(2, adm.getSenha());
            ResultSet resultado = stmt.executeQuery();
            Administrador usuarioObtido = new Administrador();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    usuarioObtido.setNome(resultado.getString("NOME"));
                    usuarioObtido.setCpf(resultado.getString("CPF"));
                    usuarioObtido.setSenha(resultado.getString("SENHA"));
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
