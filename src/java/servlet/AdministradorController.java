package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.administradorDAO;
import aplicacao.Administrador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {
    String view = "view/usuario/cadastroAdmin.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Administrador administrador = new Administrador();
        administradorDAO administradorDAO = new administradorDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "AdmIncluir":
                rd = request.getRequestDispatcher("/view/usuario/cadastroAdmin.jsp");
                break;
            case "Alterar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.getAdministrador(id);
                    administrador.setId(id);
                    rd = request.getRequestDispatcher("/view/usuario/alteraAdmin.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para alteracao de administrador");
                }
                break;
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.getAdministrador(id);
                    administrador.setId(id);
                    rd = request.getRequestDispatcher("/view/usuario/excluiAdmin.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de administrador");
                }
                break;
                default: rd = request.getRequestDispatcher("");
                break;
        }
        request.setAttribute("administrador", administrador);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String acao = request.getParameter("acao");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || senha_user.isEmpty()) {

            Administrador administrador = new Administrador();
            switch (acao) {
                /*case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        administradorDAO administradorDAO = new administradorDAO();
                        administrador = administradorDAO.getAdministrador(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de administrador");
                    }
                    break;*/
                case "AdmIncluir":
                    request.setAttribute("acao", "AdmIncluir");
                    rd = request.getRequestDispatcher(view);
                    break;
                    
                case "Excluir":
                    request.setAttribute("acao", "Excluir");
                    rd = request.getRequestDispatcher("/view/usuario/excluiAdmin.jsp");
                    break;
                default:
                    rd = request.getRequestDispatcher("");
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("administrador", administrador);
            rd.forward(request, response);

        } else {

            Administrador administrador = new Administrador(nome_user, cpf_user, senha_user);
            administradorDAO administradorDAO = new administradorDAO();

            try {
                switch (acao) {
                    case "Incluir":
                        administradorDAO.create(administrador);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            administradorDAO.update(administrador);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            administradorDAO.delete(id);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                    case "AdmIncluir":
                        administradorDAO.create(administrador);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                }

                rd = request.getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de administrador");
            }
        }
    }
}
