/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.usuarioDAO;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Ferraz-PC
 */
@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        RequestDispatcher rd ;
        switch (acao) {
            case "Login":  // chama form de login
                rd = request.getRequestDispatcher("/view/login.jsp");
                rd.forward(request, response);
                break;
            case "Logout":
                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("/view/login.jsp");
                rd.forward(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String perfil_user = request.getParameter("perfil");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/view/login.jsp");
            rd.forward(request, response);


        } else {
            Usuario usuarioObtido;
            Usuario usuario = new Usuario(cpf_user, senha_user);
            usuarioDAO usuarioDAO = new usuarioDAO();
            try {
               usuarioObtido = usuarioDAO.logar(usuario, perfil_user);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (usuarioObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioObtido);
                System.out.print(usuarioObtido.getNome() + "\n");
                rd = request.getRequestDispatcher("/DashboardController");
                rd.forward(request, response);
                
         
            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/view/login.jsp");
                rd.forward(request, response);
                
             
            }
        }
    }
}
