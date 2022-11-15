package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.administradorDAO;
import bean.classes_usuario.Administrador;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "administradorController", urlPatterns = {"/administradorController"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Administrador administrador = new Administrador();
        administradorDAO administradorDAO = new administradorDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Administrador> lista_administrador = administradorDAO.read();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("lista_administrador", lista_administrador);
                    rd = request.getRequestDispatcher("/view/administrador/listaadministrador.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar administradors (administrador) ");
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.getAdministrador(id);
                    administrador.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de administrador");
                }
                break;

        }
        request.setAttribute("administrador", administrador);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/administrador/formadministrador.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String endereco_user = request.getParameter("endereco");
        String senha_user = request.getParameter("senha");
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome_user.isEmpty() || cpf_user.isEmpty() || endereco_user.isEmpty() || senha_user.isEmpty()) {

            Administrador administrador = new Administrador();
            switch (btEnviar) {
                case "Incluir":
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
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("administrador", administrador);

            rd = request.getRequestDispatcher("/view/administrador/formadministrador.jsp");
            rd.forward(request, response);

        } else {

            Administrador administrador = new Administrador(nome_user, cpf_user, senha_user);
            administrador.setId(id);

            administradorDAO administradorDAO = new administradorDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        administradorDAO.create(administrador);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        administradorDAO.create(administrador);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        administradorDAO.create(administrador);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Administrador> listaadministrador = administradorDAO.read();
                request.setAttribute("listaadministrador", listaadministrador);

                rd = request.getRequestDispatcher("/view/administrador/listaadministradorr.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de administrador");
            }
        }
    }
}
