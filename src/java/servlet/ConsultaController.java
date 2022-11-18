/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Consulta;
import connection.ConnectionFactory;
import dao.consultaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferraz-PC
 */
@WebServlet(name = "ConsultaController", urlPatterns = {"/ConsultaController"})
public class ConsultaController extends HttpServlet {
    String view = "/view/usuario/agendaConsulta.jsp";
    String home = "/view/home.jsp";
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
                    ArrayList<aplicacao.Consulta> listaConsultas = consultaDAO.read();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher(home);
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
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
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro da consulta");
                }
                break;

        }
        request.setAttribute("consulta", consulta);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher(home);
        rd.forward(request, response);

    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        int itp_user = Integer.parseInt(request.getParameter("idtipoplano"));
        String csenha_user = request.getParameter("csenha");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || senha_user.isEmpty() || itp_user == 0) {

            Consulta consulta = new Consulta(nome_user, cpf_user);
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                /*case "Excluir":
                    try {
                        pacienteDAO pacienteDAO = new pacienteDAO();
                        paciente = pacienteDAO.getPaciente(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de usuario");
                    }
                    break;
                */    
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("usuario", usuario);
            rd = request.getRequestDispatcher(view);
            rd.forward(request, response);

        } else {
            Paciente paciente = new Paciente(nome_user, cpf_user, senha_user, itp_user);
            pacienteDAO pacienteDAO = new pacienteDAO();

            try {
                switch (acao) {
                    case "Incluir":
                        pacienteDAO.create(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        break;
                    case "Alterar":
                        pacienteDAO.update(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        pacienteDAO.delete(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                /*
                ArrayList<aplicacao.Paciente> listaUsuarios = pacienteDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);
                */
                rd = request.getRequestDispatcher("/view/login.jsp");
                rd.forward(request, response);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }
}
