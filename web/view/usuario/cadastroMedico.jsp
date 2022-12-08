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
        <h1 class="titulo-login">Cadastrar médico</h1>
        <div class="container text-center">
                <div class="row mt-5">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <div class="row">
                            <form class="form-group" action="MedicoController?acao=AdmIncluir" method="POST">
                                <div class="col-12">
                                    <input type="text" name="nome" placeholder="Nome" class=" form-control mb-3" />
                                </div>
                                <div class="col-12">
                                    <input type="text" name="cpf" placeholder="CPF" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="number" name="crm" placeholder="CRM" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="estadocrm" placeholder="Estado CRM" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="autorizado" placeholder="Autorizado" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="especialidade" placeholder="Especialidade" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">  
                                    <input type="password" name="senha" placeholder="Senha" class="form-control mb-3"/>
                                </div>                                                            
                                <div class="col-12">
                                    <input type="password" name="csenha" placeholder="Confirmar senha" class="form-control mb-3"/>
                                </div>  
                                <button type="submit" class="btn btn-primary mt-1" style="background-color: #6610f2">Cadastrar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>
