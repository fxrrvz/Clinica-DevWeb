<%-- 
    Document   : alteraConsulta
    Created on : 11/12/2022, 18:35:37
    Author     : lucas
--%>

<%@page import="aplicacao.Consulta"%>
<%@page import="dao.consultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Alterar consulta</title>
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            consultaDAO administradorDAO = new consultaDAO();
            Consulta consulta = administradorDAO.getConsulta(id);
            %>
        <h1 class="titulo-login">Alterar consulta</h1>
        <div class="container text-center">
            <div class="row mt-5">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="row">
                        <form class="form-group" action="ConsultaController?acao=Alterar" method="POST">
                            <input type="hidden" name="id" value="<%= id%>"/>
                            <input type="hidden" name="horario" value="0"/>
                            <div class="col-12">
                                <input type="text" name="data" value="<%= consulta.getData()%>" placeholder="Nome" class=" form-control mb-3" />
                            </div>
                            <div class="col-12">
                                <input type="text" name="descricao" value="<%= consulta.getDescricao()%>" placeholder="CPF" class="form-control mb-3"/>
                            </div>            
                            <div class="col-12">  
                                <input type="text" name="realizada" value="<%= consulta.getRealizada()%>" placeholder="Senha" class="form-control mb-3"/>
                            </div>
                            <div class="col-12">  
                                <input type="text" name="idmedico" value="<%= consulta.getIdMedico()%>" placeholder="Senha" class="form-control mb-3"/>
                            </div>
                            <div class="col-12">  
                                <input type="text" name="idpaciente" value="<%= consulta.getIdPaciente()%>" placeholder="Senha" class="form-control mb-3"/>
                            </div>
                            <button type="submit" class="btn btn-primary mt-3" style="background-color: #6610f2">Alterar</button>
                            <a href="home.jsp" class="btn btn-primary mt-3" style="background-color: #6610f2">Voltar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>