/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import aplicacao.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public ArrayList<Descricao> read(String tabela){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Descricao> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM " + tabela);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Descricao desc = new Descricao();
                desc.setId(rs.getInt("id"));
                desc.setDescricao(rs.getString("descricao"));
                lista.add(desc);                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(descricaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }
    
    public Descricao getDescricao(int id, String tabela) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            Descricao desc = new Descricao();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM "+tabela+" WHERE ID = ? ");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    desc.setId(Integer.parseInt(resultado.getString("ID")));
                    desc.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return desc;
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void delete(int id, String tabela) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM "+tabela+" WHERE ID = ?;");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void update(Descricao descricao, String tabela) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("UPDATE "+tabela+" SET descricao = ? WHERE ID = ?;");
            sql.setString(1, descricao.getDescricao());
            sql.setInt(2, descricao.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
}


/*public ConsultaDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/