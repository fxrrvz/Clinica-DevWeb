<%-- 
    Document   : clinicaEspecialidade
    Created on : 09/10/2022, 18:58:27
    Author     : Ferraz-PC
--%>

<%@page import="dao.descricaoDAO"%>
<%@page import="aplicacao.Descricao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Alterar especialidade</title>
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            descricaoDAO descricaoDAO = new descricaoDAO();
            Descricao desc = descricaoDAO.getDescricao(id, "especialidade");
            %>
        <h1>Alterar especialidade</h1>
        <div class="container text-center">
            <div class="row mt-5">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="row">
                        <form class="form-group" action="DescricaoController?acao=Alterar&tabela=especialidade" method="POST">
                            <input type="hidden" name="id" value="<%= desc.getId()%>" />
                            <div class="col-12">
                                <input type="text" name="descricao" placeholder="Descrição" value="<%=desc.getDescricao()%>" class=" form-control mb-3"></textarea>
                            </div> 
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary mt-3" style="background-color: #6610f2">Alterar</button>
                            </div> 
                        </form>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
            
        </div>
    </body>
</html>