<%-- 
    Document   : clinicaEspecialidade
    Created on : 09/10/2022, 18:58:27
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
        <title>Cadastrar especialidade</title>
    </head>
    <body>
        <h1>Cadastrar especialidade</h1>
        <div class="container text-center">
            <div class="row mt-5">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="row">
                        <form class="form-group" action="DescricaoController?acao=Incluir&tabela=especialidade" method="POST">
                            <div class="col-12">
                                <textarea type="text" name="descricao" placeholder="Descrição" class=" form-control mb-3"></textarea>
                            </div> 
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary mt-3" style="background-color: #6610f2">Cadastrar</button>
                            </div> 
                        </form>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
            
        </div>
    </body>
</html>