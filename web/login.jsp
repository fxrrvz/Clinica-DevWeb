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
        <h1>Cl�nica DevWeb&nbsp<img src="img/health.png" alt="aaaa" style="width:55px;height:55px;"></h1>
        <div class="container mt-5">
            <div class="row">
                <div class="col"></div>
                <div class="col-4">
                <form action="/efetuarLogin" method="POST" class="form-group">
                    <input type="text" placeholder="CPF" class="form-control mb-3"/>
                    <input type="password" placeholder="Senha" class="form-control mb-3"/>
                    <a href="efetuarLogin?aca=Incluir" id="btEnviar" class="btn btn-primary mr-3" style="background-color: #6610f2">Login</a>
                    <a href="home.jsp" class="btn btn-primary" style="background-color: #6610f2">Cadastre-se</a>
                </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
    </body>
</html>
