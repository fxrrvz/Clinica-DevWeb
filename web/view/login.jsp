<%@page import="aplicacao.Administrador"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
    </head>
    <body>
        <h1>Clínica DevWeb&nbsp<img src="img/health.png" alt="aaaa" style="width:55px;height:55px;"></h1>
        <div class="container mt-5">
            <div class="row">
                <div class="col"></div>
                <div class="col-4">
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
                
                <form class="form-group" action="AutenticaController?acao=Logar" method="POST">
                   <select class="form-select form-control-sm mb-3" multiple name="perfil" aria-label="multiple select example">
                        <option value="administrador">administrador</option>
                        <option value="paciente">paciente</option>
                        <option value="medico">medico</option>
                    </select>
                    <input type="text" placeholder="CPF" name="cpf" class="form-control mb-3"/>
                    <input type="password" placeholder="Senha" name="senha" class="form-control mb-3"/>
                    <button type="submit">Login<!--a href="#" class="btn btn-primary mr-3" style="background-color: #6610f2">Login</a--></button>
                    <a href="index.jsp" class="btn btn-primary" style="background-color: #6610f2">Voltar</a>
                </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
    </body>
</html>
