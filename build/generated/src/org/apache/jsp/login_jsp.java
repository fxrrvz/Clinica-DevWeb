package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import aplicacao.Administrador;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Login</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap/css/bootstrap.min.css\">\n");
      out.write("        <!--link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\"-->\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Clínica DevWeb&nbsp<img src=\"img/health.png\" alt=\"aaaa\" style=\"width:55px;height:55px;\"></h1>\n");
      out.write("        <div class=\"container mt-5\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col\"></div>\n");
      out.write("                <div class=\"col-4\">\n");
      out.write("                 ");

                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {
      out.write("\n");
      out.write("                        <div class=\"alert alert-danger\" role=\"alert\">\n");
      out.write("                           ");
      out.print( msgError );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("                <form class=\"form-group\">\n");
      out.write("                    <div class=\"dropdown\">\n");
      out.write("                        <button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n");
      out.write("                          Dropdown button\n");
      out.write("                        </button>\n");
      out.write("                        <ul class=\"dropdown-menu\">\n");
      out.write("                          <li><a class=\"dropdown-item\" href=\"#\">Paciente</a></li>\n");
      out.write("                          <li><a class=\"dropdown-item\" href=\"#\">Another action</a></li>\n");
      out.write("                          <li><a class=\"dropdown-item\" href=\"#\">Something else here</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                    <input type=\"text\" placeholder=\"CPF\" name=\"cpf\" class=\"form-control mb-3\"/>\n");
      out.write("                    <input type=\"password\" placeholder=\"Senha\" name=\"senha\" class=\"form-control mb-3\"/>\n");
      out.write("                    <a href=\"AutenticaController?acao=logar\" class=\"btn btn-primary mr-3\" style=\"background-color: #6610f2\">Login</a>\n");
      out.write("                    <a href=\"AdministradorController?acao=Listar\" method=\"POST\" class=\"btn btn-primary\" style=\"background-color: #6610f2\">Cadastre-se</a>\n");
      out.write("                </form>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col\"></div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
