/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Consulta;
import aplicacao.Usuario;
import dao.consultaDAO;
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
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.getConsulta(id);
                    consulta.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage()+"\n");
                    throw new RuntimeException("Falha em uma query para cadastro da consulta");
                }
                break;       
        }
        request.setAttribute("consulta", consulta);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher(view);
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
        
        if (perfil.equals("paciente")){
            idpaciente_user = usuarioLogado.getId();
            idmedico_user = Integer.parseInt(request.getParameter("idmedico"));
        }else if(perfil.equals("medico")){
            idmedico_user = usuarioLogado.getId();
            idpaciente_user = Integer.parseInt(request.getParameter("idpaciente")); 
        }else{
            idmedico_user = Integer.parseInt(request.getParameter("idmedico"));
            idpaciente_user = Integer.parseInt(request.getParameter("idpaciente")); 
        }
        
        
        String id = request.getParameter("id");
        String acao = request.getParameter("acao");
        String data_user = request.getParameter("data");
        String horario_user = request.getParameter("horario");
        String data_hora = data_user + " " + horario_user;
        String descricao_user = request.getParameter("descricao");
        String realizada = request.getParameter("realizada");

        RequestDispatcher rd;

        if (data_user.isEmpty() || descricao_user.isEmpty() || horario_user.isEmpty() || idmedico_user == 0 || idpaciente_user == 0) {

            Consulta consulta = new Consulta(data_hora, descricao_user, idmedico_user, idpaciente_user);
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                /*
                case "Excluir":
                    try {
                        consultaDAO consultaDAO = new consultaDAO();
                        consulta = consultaDAO.getConsulta(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de usuario");
                    }
                    break;
                */    
            }

            request.setAttribute("msgError", "?? necess??rio preencher todos os campos");
            request.setAttribute("consulta", consulta);
            rd = request.getRequestDispatcher(view);
            rd.forward(request, response);

        } else {
            Consulta consulta = new Consulta(data_hora, descricao_user, idmedico_user, idpaciente_user);
            consultaDAO consultaDAO = new consultaDAO();

            try {
                switch (acao) {
                    case "Incluir":
                        consultaDAO.create(consulta);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        break;
                    case "Alterar":
                        consultaDAO.update(consulta);
                        request.setAttribute("msgOperacaoRealizada", "Altera????o realizada com sucesso");
                        break;
                    case "Excluir":
                        consultaDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclus??o realizada com sucesso");
                        break;
                }
                /*
                ArrayList<aplicacao.Paciente> listaUsuarios = pacienteDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);
                */
                rd = request.getRequestDispatcher(home);
                rd.forward(request, response);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro");
            }
        }
    }
}
