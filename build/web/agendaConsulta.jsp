<%-- 
    Document   : agendaConsulta
    Created on : 08/10/2022, 22:08:04
    Author     : Ferraz-PC
--%>

<%@page import="aplicacao.Especialidade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Medico"%>
<%@page import="dao.pacienteDAO"%>
<%@page import="dao.medicoDAO"%>
<%@page import="aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Agendar consulta</title>
    </head>
    <body>
        <h1 class="titulo-login">Agendar consulta</h1>
        <div class="container text-center">
            <div class="row mt-5">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="row">
                        <%
                            String msgError = (String) request.getAttribute("msgError");
                            if ((msgError != null) && (!msgError.isEmpty())) {%>
                                <div class="alert alert-danger" role="alert">
                                   <%= msgError %>
                                </div>
                        <% }%>   

                        <%
                            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                            int id = usuarioLogado.getId();
                            request.setAttribute("idpaciente", id);
                        %>    
                        
                        <%
                            String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                            if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>
                                <div class="alert alert-success" role="alert">
                                   <%= msgOperacaoRealizada %>
                                </div>
                        <% }%>
                        <form class="form-group" action="ConsultaController?acao=Incluir" method="POST">
                            <div class="col-12">
                                <input type="date" name="data" placeholder="Data" class="form-control mb-3"/>
                            </div>   
                            <div class="col-12">
                                <input type="time" name="horario" placeholder="Horário" class="form-control mb-3"/>
                            </div> 
                        <%
                            medicoDAO medicoDAO = new medicoDAO();
                            pacienteDAO pacienteDAO = new pacienteDAO();
                            ArrayList<Medico> listaMedico = medicoDAO.read();
                            out.println("<select class='form-control mb-3' name='idmedico'");
                            for (Medico medico : listaMedico) {
                                Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                out.println("<option value='" + medico.getId() + "'>" + medico.getNome() + " - " + especialidade.getDescricao() + "</option>");
                            }
                            out.println("</select>");                     
                        %>
                        
                            <div class="col-12">
                                <textarea type="text" name="descricao" placeholder="Descrição" class=" form-control mb-3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary mt-1" style="background-color: #6610f2">Agendar</a>
                        </form>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
    </body>
</html>
