
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import aplicacao.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import aplicacao.Paciente;
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
            stmt = con.prepareStatement("INSERT INTO paciente(nome, cpf, senha, autorizado, idtipoplano) VALUES (?,?,?,?,?);");
            stmt.setString(1, pc.getNome());
            stmt.setString(2, pc.getCpf());
            stmt.setString(3, pc.getSenha());
            stmt.setString(4, pc.getAutorizado());
            stmt.setInt(5, pc.getIdTipoPlano());
            
            stmt.executeUpdate();
            
            System.out.print("Salvo com sucesso!");
            
        } catch (SQLException ex) {
            System.out.print(ex);
        }finally{
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public List<Paciente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> lista = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM paciente");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSenha(rs.getString("senha"));
                paciente.setAutorizado(rs.getString("autorizado"));
                paciente.setIdTipoPlano(rs.getInt("idtipoplano"));
                lista.add(paciente);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(pacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return lista;
        
    }
    
    public Paciente getPaciente(int id) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        try {
            Paciente paciente = new Paciente();
            stmt = con.prepareStatement("SELECT * FROM paciente WHERE ID = ?;");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    paciente.setId(Integer.parseInt(resultado.getString("ID")));
                    paciente.setNome(resultado.getString("NOME"));
                    paciente.setCpf(resultado.getString("CPF"));
                    paciente.setSenha(resultado.getString("SENHA"));
                }
            }
            return paciente;

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void update(Paciente paciente) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("UPDATE paciente SET nome = ?, cpf = ?, senha = ?, autorizado = ?, idtipoplano = ?  WHERE ID = ?;");
            sql.setString(1, paciente.getNome());
            sql.setString(2, paciente.getCpf());
            sql.setString(4, paciente.getSenha());
            sql.setString(5, paciente.getAutorizado());
            sql.setInt(6, paciente.getIdTipoPlano());
            sql.setInt(7, paciente.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }

    public void delete(Paciente paciente) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM paciente WHERE ID = ?;");
            sql.setInt(1, paciente.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
}
