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
import java.util.logging.Level;
import java.util.logging.Logger;
import aplicacao.Consulta;
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
            stmt = con.prepareStatement("INSERT INTO consulta (data, descricao, idmedico, idpaciente, realizada) VALUES(?,?,?,?,?)");
            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getDescricao());
            stmt.setInt(3, consulta.getIdMedico());
            stmt.setInt(4, consulta.getIdPaciente());
            stmt.setString(5, "N");
            
            stmt.executeUpdate();
            
            System.out.print("\nSalvo com sucesso!"+"\n");
            
        } catch (SQLException ex) {
            System.out.print("\n"+ex+"\n");
            System.out.print("\nErro ao salvar!"+"\n");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public ArrayList<Consulta> read(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Consulta> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta WHERE idpaciente=?");
            stmt.setInt(1, id);
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
    
    public Consulta getConsulta(int id) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        try {
            Consulta consulta = new Consulta();
            stmt = con.prepareStatement("SELECT * FROM consulta WHERE ID = ?;");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    consulta.setId(Integer.parseInt(resultado.getString("ID")));
                    consulta.setData(resultado.getString("DATA"));
                    consulta.setDescricao(resultado.getString("DESCRICAO"));
                    consulta.setRealizada(resultado.getString("REALIZADA"));
                    consulta.setIdMedico(resultado.getInt("IDMEDICO"));
                    consulta.setIdPaciente(resultado.getInt("IDPACIENTE"));
                }
            }
            return consulta;

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void update(Consulta consulta) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("UPDATE consulta SET data = ?, descricao = ?, realizada = ?, idmedico = ?, idpaciente = ?  WHERE ID = ?;");
            sql.setString(1, consulta.getData());
            sql.setString(2, consulta.getDescricao());
            sql.setString(4, consulta.getRealizada());
            sql.setInt(5, consulta.getIdMedico());
            sql.setInt(6, consulta.getIdPaciente());
            sql.setInt(7, consulta.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }

    public void delete(Consulta consulta) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM consulta WHERE ID = ?;");
            sql.setInt(1, consulta.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
}


/*public ConsultaDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/