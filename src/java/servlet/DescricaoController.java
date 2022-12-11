/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import aplicacao.Descricao;
import aplicacao.Especialidade;
import aplicacao.Paciente;
import aplicacao.Usuario;
import dao.descricaoDAO;
import dao.pacienteDAO;
import java.io.IOException;
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
@WebServlet(name = "DescricaoController", urlPatterns = {"/DescricaoController"})
public class DescricaoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = (String) request.getParameter("acao");

        Descricao descricao = new Descricao();
        descricaoDAO descricaoDAO = new descricaoDAO();
        String tabela = request.getParameter("tabela");
        RequestDispatcher rd;
        String cadastro = "";
        String altera = "";
        String exclui = "";
        switch (tabela){
            case "especialidade":
                cadastro = "clinicaEspecialidade.jsp"; 
                altera = "/view/usuario/alteraEspecialidade.jsp";
                exclui = "/view/usuario/excluiEspecialidade.jsp";
                break;
            case "tipoplano":
                cadastro = "cadastroPlano.jsp";
                altera = "/view/usuario/alteraPlano.jsp";
                exclui = "/view/usuario/excluiPlano.jsp";
                break;
            case "tipoexame":
                cadastro = "cadastroTipoExame.jsp";
                altera = "/view/usuario/alteraTipoExame.jsp";
                exclui = "/view/usuario/excluiTipoExame.jsp";
                break;
        }
        switch (acao) {
            case "Incluir":
                rd = request.getRequestDispatcher(cadastro);
                break;
            case "Alterar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    descricao = descricaoDAO.getDescricao(id, tabela);
                    descricao.setId(id);
                    rd = request.getRequestDispatcher(altera);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha get descricao controller");
                }
                break;
            case "Excluir":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    descricao = descricaoDAO.getDescricao(id, tabela);
                    descricao.setId(id);
                    rd = request.getRequestDispatcher(exclui);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha no get descricao controller");
                }
                break;
                default: rd = request.getRequestDispatcher("");
                break;
        }
        request.setAttribute("descricao", descricao);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        
        rd.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        String tabela = request.getParameter("tabela");
        String acao = request.getParameter("acao");
        String view = "";
        switch (tabela){
            case "especialidade":
                view = "clinicaEspecialidade.jsp"; 
                break;
            case "tipoplano":
                view = "cadastroPlano.jsp";
                break;
            case "tipoexame":
                view = "cadastroTipoExame.jsp";
                break;
        }
        RequestDispatcher rd;

        if (descricao.isEmpty()) {
            switch (acao) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                    request.setAttribute("acao", "Incluir");
                    break; 
            }

            request.setAttribute("msgError", "É necessário preencher a descrição");
            rd = request.getRequestDispatcher(view);
            rd.forward(request, response);

        } else {
            Descricao desc = new Descricao(descricao);
            descricaoDAO dao = new descricaoDAO();

            try {
                switch (acao) {
                    case "Incluir":
                        dao.create(desc, tabela);
                        request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso!");
                        break;
                    case "Alterar":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            desc.setId(id);
                            dao.update(desc, tabela);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            dao.delete(id, tabela);
                        } catch (Exception ex) {
                            Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                /*
                ArrayList<aplicacao.Paciente> listaUsuarios = pacienteDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);
                */
                rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }    
}
