import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import aplicacao.Administrador;
import aplicacao.Paciente;
import aplicacao.Medico;
import dao.administradorDAO;
import dao.pacienteDAO;
import dao.medicoDAO;

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
            request.setAttribute("msgError", "CPF e/ou senha incorreto");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else {

            Administrador adm = new Administrador(cpf_user, senha_user);
            administradorDAO admDAO = new administradorDAO();
            try {
                adm = admDAO.logar(adm);
            } catch (Exception ex) {
                throw new RuntimeException("Falha na query para Logar");
            }

            if (adm != null) {
                HttpSession session = request.getSession();
                session.setAttribute("cpf", adm);
                RequestDispatcher rd = request.getRequestDispatcher("/areaRestrita.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "CPF e/ou senha incorreto");
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }

        }
    }

}
