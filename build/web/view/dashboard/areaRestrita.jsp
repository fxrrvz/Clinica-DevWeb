<%-- 
    Document   : areaRestrita
    Created on : 17/11/2022, 03:18:20
    Author     : Ferraz-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="aplicacao.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Exemplo MVC</title>
        <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="/view/home.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <%
                    String perfil = (String) session.getAttribute("perfil");
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                    out.println("<h3>Usuário logado com sucesso</h3>");
                    out.println("<h2>Nome: " + usuarioLogado.getNome() + "</h2>");
                    out.println("<h2>CPF: " + usuarioLogado.getCpf()+ "</h2>");
                    out.println("<h2>Perfil: " + perfil + "</h2>");
                    
                %>


            </div>
        </div>
        <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
