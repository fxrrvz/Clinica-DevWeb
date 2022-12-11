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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            stmt = con.prepareStatement("INSERT INTO medico(nome, cpf, senha, crm, estadocrm, autorizado, idespecialidade)" +
                                        " VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, med.getNome());
            stmt.setString(2, med.getCpf());
            stmt.setString(3, med.getSenha());
            stmt.setInt   (4, med.getCrm());
            stmt.setString(5, med.getEstadoCrm());
            stmt.setString(6, med.getAutorizado());
            stmt.setInt   (7, med.getIdEspecialidade());
            
            stmt.executeUpdate();
            
            System.out.print("Salvo com sucesso!");
            
        } catch (SQLException ex) {
            System.out.print(ex);
            System.out.print("Erro ao salvar!");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(int id) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM medico WHERE ID = ?;");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void update(Medico medico) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement sql = con.prepareStatement("UPDATE medico SET nome = ?, cpf = ?, senha = ?, crm = ?, estadocrm = ?, autorizado = ?  WHERE ID = ?");
            sql.setString(1, medico.getNome());
            sql.setString(2, medico.getCpf());
            sql.setString(3, medico.getSenha());
            sql.setInt(4, medico.getCrm());
            sql.setString(5, medico.getEstadoCrm());
            sql.setString(6, medico.getAutorizado());
            sql.setInt(7, medico.getId());
            sql.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public ArrayList<Medico> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Medico> lista = new ArrayList<>();
        
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
    
    public Medico getMedico(int id) throws Exception {
    Connection con = ConnectionFactory.getConnection();
        try {
            Medico medico = new Medico();
            PreparedStatement sql = con.prepareStatement("SELECT * FROM medico WHERE id = ?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    medico.setId(Integer.parseInt(rs.getString("id")));
                    medico.setNome(rs.getString("nome"));
                    medico.setCpf(rs.getString("cpf"));
                    medico.setSenha(rs.getString("senha"));
                    medico.setCrm(rs.getInt("crm"));
                    medico.setEstadoCrm(rs.getString("estadocrm"));
                    medico.setAutorizado(rs.getString("autorizado"));
                    medico.setIdEspecialidade(rs.getInt("idespecialidade"));
                }
            }
            return medico;

        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public Especialidade getEspecialidade(int id) throws Exception {
    Connection con = ConnectionFactory.getConnection();
        try {
            Especialidade especialidade = new Especialidade();
            PreparedStatement sql = con.prepareStatement("SELECT DISTINCT * FROM especialidade WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    especialidade.setId(Integer.parseInt(resultado.getString("ID")));
                    especialidade.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return especialidade;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
}


/*public medicoDAO(Usuario user, String banco) {
        super.create(user, banco);
    }*/