<%-- 
    Document   : cadastroPaciente
    Created on : 08/10/2022, 20:57:05
    Author     : Ferraz-PC
--%>

<%@page import="dao.pacienteDAO"%>
<%@page import="aplicacao.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Alterar paciente</title>
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            pacienteDAO pacienteDAO = new pacienteDAO();
            Paciente paciente = pacienteDAO.getPaciente(id);
            %>
        <div>
            <h1>Alterar paciente</h1>
            <div class="container text-center">
                <div class="row mt-5">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <div class="row">
                            <form class="form-group" action="PacienteController?acao=Alterar" method="POST">
                                <input type="hidden" name="id" value="<%= id%>"/>
                                <div class="col-12">
                                    <input type="text" placeholder="Nome" name="nome" value="<%= paciente.getNome()%>" class=" form-control mb-3" />
                                </div>
                                <div class="col-12">
                                    <input type="text" placeholder="CPF" name="cpf" value="<%= paciente.getCpf()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" placeholder="Autorizado" name="autorizado" Readonly value="<%= paciente.getAutorizado()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" placeholder="Tipo plano" name="idtipoplano" value="<%= paciente.getIdTipoPlano()%>" class="form-control mb-3"/>
                                </div>                
                                <div class="col-12">  
                                    <input type="password" placeholder="Senha" name="senha" value="<%= paciente.getSenha()%>" class="form-control mb-3"/>
                                </div>
                                <button class="btn btn-primary mt-3" type="submit" style="background-color: #6610f2">Alterar</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
        </div>
    </body>
</html>
