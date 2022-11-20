<%-- 
    Document   : home
    Created on : 08/10/2022, 00:23:50
    Author     : Ferraz-PC
--%>
<%@page import="aplicacao.Usuario"%>
<%@page import="aplicacao.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.consultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Home</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" style="margin-left:2%; margin-right: 40px">Clínica DevWeb&nbsp<img src="img/health.png" alt="aaaa" style="width:30px;height:30px;"></a>
            
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                        <%
                            String perfil = (String) session.getAttribute("perfil");
                            switch (perfil) {
                                case "paciente":%>
                                <li class="nav-item">
                                    <a href="agendaConsulta.jsp" class="nav-link" style="margin-right:30px;">Agendar consulta</a>
                                </li>
                        <%      break;  
                            case "administrador":%>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroPlano.jsp">Cadastrar plano</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroAdmin.jsp">Cadastrar administrador</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroMedico.jsp">Cadastrar médico</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="clinicaEspecialidade.jsp">Clinica especialidade</a>
                                </li>
                        <%      break;
                            case "medico":%>  
                                <li class="nav-item active">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroExame.jsp">Cadastro Exame</a>
                                </li>
                        <%      break;
                            }%>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item">
                            <a class="nav-link" style="margin-right:30px;" href="index.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <h1>Home</h1>
        <div class="container mt-5">
            <div class="row">
                <form class="form-group" action="" method="">
                    
                    
                    <%
                            String msgError = (String) request.getAttribute("msgError");
                            if ((msgError != null) && (!msgError.isEmpty())) {%>
                                <div class="alert alert-danger" role="alert">
                                   <%= msgError %>
                                </div>
                        <% }%>

                        <%
                            String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                            if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>
                                <div class="alert alert-success" role="alert">
                                   <%= msgOperacaoRealizada %>
                                </div>
                        <% }%>
                        
                        
                    <table class="table form-control-sm">
                            <thead>
                            <%    
                              switch (perfil) {
                                  case "paciente" :
                                      ArrayList<Consulta> consulta = (ArrayList<Consulta>) request.getAttribute("consulta");
                            %>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Data</th>
                                      <th scope="col">Descrição</th>
                                      <th scope="col">Realizada</th>
                                      <th scope="col">Medico</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                            <%         
                                     for(Consulta c : consulta){
                                        out.println("<tr>");
                                        out.println("<td>"+ c.getId() +"</td>");
                                        out.println("<td>"+ c.getData()+"</td>");
                                        out.println("<td>"+ c.getDescricao() +"</td>");
                                        out.println("<td>"+ c.getRealizada() +"</td>");
                                        out.println("<td>"+ c.getIdMedico() +"</td>");
                                        out.println("</tr>");
                            }       
                            %>
                                    </tbody>
                            <%      break;
                                  case "administrador" :%>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">First</th>
                                      <th scope="col">Last</th>
                                      <th scope="col">Handle</th>
                                    </tr>
                            <%        break;
                                  case "medico" :%>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">First</th>
                                      <th scope="col">Last</th>
                                      <th scope="col">Handle</th>
                                    </tr>
                            <%        break;
                              }%>
                    </table>
                </form>    
            </div>             
        </div>                
    </body>
</html>
