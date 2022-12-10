<%-- 
    Document   : home
    Created on : 08/10/2022, 00:23:50
    Author     : Ferraz-PC
--%>
<%@page import="dao.administradorDAO"%>
<%@page import="aplicacao.Administrador"%>
<%@page import="aplicacao.Paciente"%>
<%@page import="dao.pacienteDAO"%>
<%@page import="dao.examesDAO"%>
<%@page import="aplicacao.Exames"%>
<%@page import="aplicacao.Descricao"%>
<%@page import="dao.descricaoDAO"%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="aplicacao.Medico"%>
<%@page import="dao.medicoDAO"%>
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
                                    <a class="nav-link" style="margin-right:30px;" href="AdministradorController?acao=AdmIncluir">Cadastrar administrador</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="MedicoController?acao=AdmIncluir">Cadastrar médico</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="clinicaEspecialidade.jsp">Cadastrar especialidade</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroTipoExame.jsp">Cadastrar Tipo Exame</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroPlano.jsp">Cadastrar Tipo Plano</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="PacienteController?acao=AdmIncluir">Cadastrar Paciente</a>
                                </li>
                        <%      break;
                            case "medico":%>
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
                              
                    <table class="table table-light table-striped table-borderless form-control-sm">
                            <thead>
                            <%
                              medicoDAO medicoDAO = new medicoDAO();
                              ArrayList<Consulta> consulta = (ArrayList<Consulta>) request.getAttribute("consulta");  
                              switch (perfil) {
                                  case "paciente" :
                            %>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Data</th>
                                      <th scope="col">Descrição</th>
                                      <th scope="col">Realizada</th>
                                      <th scope="col">Medico</th>
                                      <th scope="col">Especialidade</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                            <%         
                                     for(Consulta c : consulta){
                                        Medico medico = (Medico) medicoDAO.getMedico(c.getIdMedico());
                                        Especialidade especialidade = (Especialidade) medicoDAO.getEspecialidade(medico.getIdEspecialidade());
                                        out.println("</select>"); 
                                        out.println("<tr>");
                                        out.println("<td>"+ c.getId() +"</td>");
                                        out.println("<td>"+ c.getData()+"</td>");
                                        out.println("<td>"+ c.getDescricao() +"</td>");
                                        out.println("<td>"+ c.getRealizada() +"</td>");
                                        out.println("<td>" + medico.getNome() + "</td>");
                                        out.println("<td>" + especialidade.getDescricao() + "</td>");
                                        }
                                        out.println("</tr>");
                                    break;
                                    
                                    
                                    case "administrador" :%>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Especialidades</th>
                                      <th scope="col">Excluir</th>
                                      <th scope="col">Alterar</th>
                                    </tr>
                                    
                                    <%
                            descricaoDAO descricaoDAO = new descricaoDAO();
                            String banco = "especialidade";
                            ArrayList<Descricao> listaEspecialidades = descricaoDAO.read(banco);

                            for (Descricao especialidade : listaEspecialidades) {
                                out.println("<tr>");
                                out.println("<th>" + especialidade.getId() + "</th>");
                                out.println("<th>" + especialidade.getDescricao() + "</th>");%>

                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=especialidade.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=especialidade.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>

                        <%      out.println("</tr>");
                            }
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Planos</th>
                                <th scope="col">Excluir</th>
                                <th scope="col">Alterar</th>
                              </tr>
                        <%      
                            banco = "tipoplano";
                            ArrayList<Descricao> listaPlanos = descricaoDAO.read(banco);
                            for (Descricao plano : listaPlanos) {
                                out.println("<tr>");
                                out.println("<th>" + plano.getId() + "</th>");
                                out.println("<th>" + plano.getDescricao() + "</th>");%>

                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=plano.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=plano.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>

                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");
                            
                            //
                            //LISTA CONSULTAS
                            //
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#ID Exame</th>
                                <th scope="col">ID Tipo Exame</th>
                                <th scope="col">ID Consulta</th>
                                <th scope="col">Excluir</th>
                                <th scope="col">Alterar</th>
                              </tr>
                        <%  
                            examesDAO examesDAO = new examesDAO();
                            ArrayList<Exames> listaExames = examesDAO.read();
                            for (Exames exames : listaExames) {
                                out.println("<tr>");
                                out.println("<th>" + exames.getId() + "</th>");
                                out.println("<th>" + exames.getIdTipoExame() + "</th>");
                                out.println("<th>" + exames.getIdConsulta() + "</th>");%>

                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=exames.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=exames.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>

                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");

                        //LISTA CONSULTAS
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#ID Consulta</th>
                                <th scope="col">Data</th>
                                <th scope="col">Descricao</th>
                                <th scope="col">Realizada</th>
                                <th scope="col">IdMedico</th>
                                <th scope="col">IdPaciente</th>
                              </tr>
                        <%
                            consultaDAO consultaDAO = new consultaDAO();
                            ArrayList<Consulta> listaConsultas = consultaDAO.readAll();
                            for (Consulta con : listaConsultas) {
                                out.println("<tr>");
                                out.println("<th>" + con.getId() + "</th>");
                                out.println("<th>" + con.getData() + "</th>");
                                out.println("<th>" + con.getDescricao() + "</th>");
                                out.println("<th>" + con.getRealizada() + "</th>");
                                out.println("<th>" + con.getIdMedico() + "</th>");
                                out.println("<th>" + con.getIdPaciente() + "</th>");%>

                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");

                            //LISTA MEDICOS
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#ID Medico</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CRM</th>
                                <th scope="col">Estado CRM</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Autorizado</th>
                                <th scope="col">ID Especialidade</th>
                                <th scope="col">Excluir</th>
                                <th scope="col">Alterar</th>
                              </tr>
                        <%
                            ArrayList<Medico> listaMedicos = medicoDAO.read();
                            for (Medico med : listaMedicos) {
                                out.println("<tr>");
                                out.println("<th>" + med.getId() + "</th>");
                                out.println("<th>" + med.getNome() + "</th>");
                                out.println("<th>" + med.getCrm() + "</th>");
                                out.println("<th>" + med.getEstadoCrm() + "</th>");
                                out.println("<th>" + med.getCpf() + "</th>");
                                out.println("<th>" + med.getAutorizado() + "</th>");
                                out.println("<th>" + med.getIdEspecialidade() + "</th>");%>
                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=med.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=med.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>
                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");

                            //LISTA Pacientes
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#ID Paciente</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Autorizado</th>
                                <th scope="col">ID Tipo Plano</th>
                                <th scope="col">Excluir</th>
                                <th scope="col">Alterar</th>
                              </tr>
                        <%
                            pacienteDAO pacienteDAO = new pacienteDAO();
                            ArrayList<Paciente> listaPacientes = pacienteDAO.read();
                            for (Paciente paciente : listaPacientes) {
                                out.println("<tr>");
                                out.println("<th>" + paciente.getId() + "</th>");
                                out.println("<th>" + paciente.getNome() + "</th>");
                                out.println("<th>" + paciente.getCpf() + "</th>");
                                out.println("<th>" + paciente.getAutorizado() + "</th>");
                                out.println("<th>" + paciente.getIdTipoPlano() + "</th>");%>
                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=paciente.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=paciente.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>
                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");

                            //LISTA ADMIN
                            out.println("<table class='table table-light table-striped table-borderless form-control-sm'>");
                            out.println("<tbody>");%>
                            <tr>
                                <th scope="col">#ID Admin</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Excluir</th>
                                <th scope="col">Alterar</th>
                              </tr>
                        <%
                            administradorDAO administradorDAO = new administradorDAO();
                            ArrayList<Administrador> listaAdmin = administradorDAO.read();
                            for (Administrador adm : listaAdmin) {
                                out.println("<tr>");
                                out.println("<th>" + adm.getId() + "</th>");
                                out.println("<th>" + adm.getNome() + "</th>");
                                out.println("<th>" + adm.getCpf() + "</th>");%>
                            <td><a href="TipoPlanoServlet?acao=Excluir&id=<%=adm.getId()%>" type="submit" name="acao" class="btn btn-danger">Excluir</button></td>
                            <td><a href="TipoPlanoServlet?acao=Alterar&id=<%=adm.getId()%>" type="submit" name="acao" class="btn btn-warning">Alterar</a></td>
                        <%      out.println("</tr>");
                            }
                            out.println("</tbody>");
                            out.println("</table>");
                    %>
                            <%        break;
                            
                            
                            
                            
                                  case "medico" :%>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Data</th>
                                      <th scope="col">Descrição</th>
                                      <th scope="col">Realizada</th>
                                      <th scope="col">Paciente</th>
                                      <th scope="col">Realizar Consulta</th>
                                    </tr>
                            <%        
                                     for(Consulta c : consulta){
                                        out.println("<tr>");
                                        out.println("<td>"+ c.getId() +"</td>");
                                        out.println("<td>"+ c.getData()+"</td>");
                                        out.println("<td>"+ c.getDescricao() +"</td>");
                                        out.println("<td>"+ c.getRealizada() +"</td>");
                                        out.println("<td>"+ c.getIdPaciente() +"</td>");
                                        if(c.getRealizada().equals("N")){
                                            out.println("<td><a href='ConsultaController?acao=RealizarConsulta&method=get&idconsulta="+c.getId()+"' class='btn btn-primary' >Realizar Consulta</a></td>");
                                        }else{
                                            out.println("<td><a class='btn btn-primary' style='background-color: #808080'>Realizar Consulta</a></td>");
                                        }
                                        //out.println("<td><a href='ConsultaController?acao=Excluir&method=post&idconsulta="+c.getId()+"' class='btn btn-primary' >Excluir</a></td>");
                                        out.println("</tr>");
                                     }
                                   break;
                            }%>
                            </tbody>
                    </table>
                </form>
            </div>             
        </div>                
    </body>
</html>
