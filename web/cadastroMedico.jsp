<%-- 
    Document   : cadastroMedico
    Created on : 09/10/2022, 18:54:30
    Author     : Ferraz-PC
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Cadastrar médico</title>
    </head>
    <body>
        <div class="login-page">
            <h1 class="titulo-login">Cadastrar médico</h1>
            <div class="form">
                <form class="login-form">
                    <input type="text" placeholder="CPF"/>                    
                    <input type="text" placeholder="Nome"/>
                    <input type="text" placeholder="CRM"/>
                    <input type="text" placeholder="Estado CRM"/>
                    <input type="text" placeholder="Autorizado"/>
                    <input type="text" placeholder="Especialidade"/>
                    <input type="password" placeholder="Senha"/>
                    <input type="password" placeholder="Confirmar senha"/>
                    <button><a href="home.jsp">cadastrar</a></button>
                </form>
            </div>
        </div>
    </body>
</html>
