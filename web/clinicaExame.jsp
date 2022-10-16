<%-- 
    Document   : clinicaExame
    Created on : 09/10/2022, 18:57:26
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
        <title>Clinica exame</title>
    </head>
    <body>
        <div class="container">
            <h1 >Clinica exame</h1>
            <div class="form-group">        
                <form class="cadastro-form">
                    <input type="text" placeholder="id clinica" class="form-control"/>
                    <input type="text" placeholder="id consulta" class="form-control"/>
                    <!--button type="submit" class="text-center" formaction="home.jsp">agendar</button-->
                    <a href="home.jsp" class="btn btn-primary" style="background-color: #6610f2">agendar</a>
                </form>
            </div>
        </div>
    </body>
</html>
