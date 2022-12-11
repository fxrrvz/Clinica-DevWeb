<%-- 
    Document   : cadastroPaciente
    Created on : 08/10/2022, 20:57:05
    Author     : Ferraz-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Cadastrar paciente</title>
    </head>
    <body>
        <div>
            <%
                String acao;
                try{
                    String perfil = (String) session.getAttribute("perfil");
                    
                    if (perfil.equals("administrador")){
                        acao = "AdmIncluir";
                    } else {
                        acao = "Incluir";
                    }
                } catch (Exception ex) {
                    acao = "Incluir";
                    System.out.println(ex.getMessage());
                    //throw new RuntimeException("Falha cadastro paciente perfil");
                }
                %>
            <h1>Cadastrar paciente</h1>
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
                            <form class="form-group" action="PacienteController?acao=<%= acao%>" method="POST">
                                <div class="col-12">
                                    <input type="text" placeholder="Nome" name="nome" class=" form-control mb-3" />
                                </div>
                                <div class="col-12">
                                    <input type="text" placeholder="CPF" name="cpf" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" placeholder="Tipo plano" name="idtipoplano" class="form-control mb-3"/>
                                </div>                
                                <div class="col-12">  
                                    <input type="password" placeholder="Senha" name="senha" class="form-control mb-3"/>
                                </div>                                                            
                                <div class="col-12">
                                    <input type="password" placeholder="Confirmar senha" name="csenha" class="form-control mb-3"/>
                                </div>
                                <button class="btn btn-primary mt-3" type="submit" style="background-color: #6610f2">Cadastrar</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
        </div>
    </body>
</html>
