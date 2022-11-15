import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.classes_usuario.Usuario;
import dao.usuarioDAO;

@WebServlet(urlPatterns = {"/efetuarLogin"})
public class efetuarLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else {

            Usuario usuario = new Usuario(cpf_user, senha_user);
            usuarioDAO usuarioDAO = new usuarioDAO();
            try {
                usuario = usuarioDAO.logar(usuario);
            } catch (Exception ex) {
                throw new RuntimeException("Falha na query para Logar");
            }

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("/areaRestrita.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }

        }
    }

}
