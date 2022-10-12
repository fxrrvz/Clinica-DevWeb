<%-- 
    Document   : agendaConsulta
    Created on : 08/10/2022, 22:08:04
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
        <title>Agendar consulta</title>
    </head>
    <body>
        <div class="login-page">
            <h1 class="titulo-login">Agendar consulta</h1>
            <div class="form">
                <form class="login-form">                    
                    <input type="text" placeholder="Descrição"/>
                    <input type="text" placeholder="Data"/>
                    <input type="text" placeholder="Realizada"/>
                    <input type="text" placeholder="id paciente"/>
                    <input type="text" placeholder="id medico"/>
                    <button type="submit" formaction="home.jsp">agendar</button>
                </form>
            </div>
        </div>
    </body>
</html>
