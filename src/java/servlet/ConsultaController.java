/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Consulta;
import aplicacao.Exames;
import aplicacao.Usuario;
import dao.consultaDAO;
import dao.examesDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ferraz-PC
 */
@WebServlet(name = "ConsultaController", urlPatterns = {"/ConsultaController"})
public class ConsultaController extends HttpServlet {
    String view = "agendaConsulta.jsp";
    String home = "home.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        String idconsulta = request.getParameter("idconsulta");
        
        Consulta consulta = new Consulta();
        consultaDAO consultaDAO = new consultaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<aplicacao.Consulta> listaConsultas = consultaDAO.read(consulta.getId());
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher(home);
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage()+"\n");
                    throw new RuntimeException("Falha na query listar consultas (Consulta) ");
                
                }
                break;
            case "Alterar":
            case "RealizarConsulta":
                try {
                    request.setAttribute("idconsulta", idconsulta);
                    rd = request.getRequestDispatcher("realizaConsulta.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage()+"\n");
                    throw new RuntimeException("Falha servlet Realizar Consulta GET");
                }
                break;
            case "Excluir":
                /*try {
                    consulta = consultaDAO.getConsulta(id);
                    consulta.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage()+"\n");
                    throw new RuntimeException("Falha em uma query para cadastro da consulta");
                }
                break;*/ 
            default:
                rd = request.getRequestDispatcher(home);
                break;
        }
        request.setAttribute("consulta", consulta);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);
        rd.forward(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        String perfil = (String) session. getAttribute("perfil");
        int idpaciente_user;
        int idmedico_user;
        String descricao_user;
        String realizada;
        String idtipoexame = "";
        int idconsulta = 0;
        switch (perfil) {
            case "paciente":
                idpaciente_user = usuarioLogado.getId();
                idmedico_user = Integer.parseInt(request.getParameter("idmedico"));
                descricao_user = "Pendente";
                realizada = "N";
                break;
            case "medico":
                idmedico_user = usuarioLogado.getId();
                idpaciente_user = 0;
                descricao_user = request.getParameter("descricao");
                idtipoexame = request.getParameter("idtipoexame");
                idconsulta = Integer.parseInt(request.getParameter("idconsulta"));
                realizada = "S";
                break;
            default:
                idmedico_user = Integer.parseInt(request.getParameter("idmedico"));
                idpaciente_user = Integer.parseInt(request.getParameter("idpaciente"));
                descricao_user = request.getParameter("descricao");
                realizada = request.getParameter("realizada");
                break;
        }
        
        
        String id = request.getParameter("id");
        String acao = request.getParameter("acao");
        String data_user = request.getParameter("data");
        String horario_user = request.getParameter("horario");
        String data_hora = data_user + " " + horario_user;

        RequestDispatcher rd;

        if (data_user.isEmpty() || descricao_user.isEmpty() || horario_user.isEmpty() || idmedico_user == 0 || idpaciente_user == 0) {

            Consulta consulta = new Consulta(data_hora, descricao_user, idmedico_user, idpaciente_user);
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    break;
                case "Alterar":
                case "RealizarConsulta":
                case "Excluir":
                    request.setAttribute("acao", "Excluir");
                    request.setAttribute("msgError", "valor 0 ou nulo");
                    break;
            }

            
            request.setAttribute("consulta", consulta);
            rd = request.getRequestDispatcher(view);
            rd.forward(request, response);

        } else {
            Consulta consulta = new Consulta(data_hora, descricao_user, idmedico_user, idpaciente_user, realizada);
            consultaDAO consultaDAO = new consultaDAO();
            examesDAO examesDAO = new examesDAO();
            ArrayList<Consulta> consultas = new ArrayList<Consulta>();
            try {
                switch (acao) {
                    case "Incluir":
                        consultaDAO.create(consulta);
                        consultas = (ArrayList<Consulta>) consultaDAO.read(usuarioLogado.getId());
                        request.setAttribute("consulta", consultas);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        rd = request.getRequestDispatcher("home.jsp");
                        break;
                    case "Alterar":
                        consultaDAO.update(consulta);
                        consultas = (ArrayList<Consulta>) consultaDAO.read(usuarioLogado.getId());
                        request.setAttribute("consulta", consultas);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        rd = request.getRequestDispatcher("home.jsp");
                        break;
                    case "RealizarConsulta":
                        consultaDAO.realizaConsulta(idconsulta, descricao_user);
                        if(!idtipoexame.equals("")){
                            Exames exame = new Exames(Integer.parseInt(idtipoexame), idconsulta);
                            examesDAO.create(exame);
                        }
                        consultas = (ArrayList<Consulta>) consultaDAO.read(usuarioLogado.getId());
                        request.setAttribute("consulta", consultas);
                        request.setAttribute("msgOperacaoRealizada", "Consulta realizada com sucesso");
                        rd = request.getRequestDispatcher("home.jsp");
                        break;
                    case "Excluir":
                        consultaDAO.delete(id);
                        consultas = (ArrayList<Consulta>) consultaDAO.read(usuarioLogado.getId());
                        request.setAttribute("consulta", consultas);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        rd = request.getRequestDispatcher("home.jsp");
                        break;
                
                }
                /*
                ArrayList<aplicacao.Paciente> listaUsuarios = pacienteDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);
                */
                consultas = (ArrayList<Consulta>) consultaDAO.read(usuarioLogado.getId());
                request.setAttribute("consulta", consultas);
                rd = request.getRequestDispatcher(home);
                rd.forward(request, response);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro");
            }
        }
    }
}
