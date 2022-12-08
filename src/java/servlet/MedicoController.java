/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Medico;
import aplicacao.Usuario;
import dao.medicoDAO;
import java.io.IOException;
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
@WebServlet(name = "MedicoController", urlPatterns = {"/MedicoController"})
public class MedicoController extends HttpServlet {
    String view = "view/usuario/cadastroMedico.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Medico usuario = new Medico();
        medicoDAO medicoDAO = new medicoDAO();
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
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    usuario = medicoDAO.getMedico(id);
                    usuario.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;

        }
        request.setAttribute("usuario", usuario);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher(view);
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        int crm = Integer.parseInt(request.getParameter("crm"));
        String estadocrm = request.getParameter("estadocrm");
        String autorizado = request.getParameter("autorizado");
        int idespecialidade = Integer.parseInt(request.getParameter("especialidade"));
        String senha = request.getParameter("senha");
        String csenha_user = request.getParameter("csenha");

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || estadocrm.isEmpty() || idespecialidade == 0 || crm == 0 || autorizado.isEmpty()) {

            Usuario usuario = new Usuario(nome, cpf);
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "AdmIncluir":
                request.setAttribute("acao", "AdmIncluir");
                break;
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
            Medico medico = new Medico(nome, cpf, senha, crm, estadocrm, autorizado, idespecialidade);
            medicoDAO medicoDAO = new medicoDAO();

            try {
                switch (acao) {
                    /*case "Incluir":
                        medicoDAO.create(medico);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                        break;
                    case "Alterar":
                        /*medicoDAO.update(medico);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                        break;*/
                    case "Excluir":
                        /*medicoDAO.delete(medico);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                        break;*/
                    case "AdmIncluir":
                        medicoDAO.create(medico);
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
