/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Paciente;
import aplicacao.Usuario;
import connection.ConnectionFactory;
import dao.pacienteDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {
    String view = "/view/usuario/cadastroPaciente.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Paciente usuario = new Paciente();
        pacienteDAO pacienteDAO = new pacienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            /*
            case "Listar":
                try {
                    ArrayList<aplicacao.Usuario> listaUsuarios = pacienteDAO.ListaDePacientes();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaUsuarios", listaUsuarios);
                    rd = request.getRequestDispatcher("/view/usuario/listaUsuarios.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar usuarios (Usuario) ");
                }
                break;
            */
            case "Incluir":
                try{
                    rd = request.getRequestDispatcher("/view/usuario/cadastroPaciente.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha no get cadastroPaciente ");
                }
                break;
            case "AdmIncluir":
                try{
                    rd = request.getRequestDispatcher("/view/usuario/cadastroPaciente.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha no get cadastroPaciente ");
                }
                break;
            case "Alterar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    usuario = pacienteDAO.getPaciente(id);
                    usuario.setId(id);
                    rd = request.getRequestDispatcher("/view/usuario/alteraPaciente.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para alteracao de medico");
                }
                break;
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    usuario = pacienteDAO.getPaciente(id);
                    usuario.setId(id);
                    rd = request.getRequestDispatcher("/view/usuario/excluiPaciente.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para exclusao de medico");
                }
                break;
            default:
                rd = request.getRequestDispatcher("");
                break;

        }
        request.setAttribute("usuario", usuario);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

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

            Usuario usuario = new Usuario(nome_user, cpf_user);
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    rd = request.getRequestDispatcher(view);
                    break;
                case "Alterar":
                    request.setAttribute("acao", "AdmIncluir");
                    rd = request.getRequestDispatcher("/view/usuario/alteraPaciente.jsp");
                    break;
                case "AdmIncluir":
                    request.setAttribute("acao", "AdmIncluir");
                    rd = request.getRequestDispatcher(view);
                break;
                case "Excluir":
                    request.setAttribute("acao", "Excluir");
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    rd = request.getRequestDispatcher("/view/usuario/excluiPaciente.jsp");
                    break;
                default:
                    rd = request.getRequestDispatcher("");
                    break;   
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("usuario", usuario);
            rd.forward(request, response);

        } else {
            Paciente paciente = new Paciente(nome_user, cpf_user, senha_user, itp_user);
            pacienteDAO pacienteDAO = new pacienteDAO();

            try {
                switch (acao) {
                    case "Incluir":
                        pacienteDAO.create(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                        break;
                    case "Alterar":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            String autorizado = request.getParameter("autorizado");
                            paciente.setId(id);
                            paciente.setAutorizado(autorizado);
                            pacienteDAO.update(paciente);
                            rd = request.getRequestDispatcher("/home.jsp");
                            request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                            rd.forward(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "Excluir":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            pacienteDAO.delete(id);
                            rd = request.getRequestDispatcher("/home.jsp");
                            request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                            rd.forward(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "AdmIncluir":
                        pacienteDAO.create(paciente);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        rd = request.getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);
                        break;
                }
                /*
                ArrayList<aplicacao.Paciente> listaUsuarios = pacienteDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);
                */
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }
}
