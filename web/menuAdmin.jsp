<%-- 
    Document   : menuAdmin
    Created on : 08/12/2022, 20:28:40
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" style="margin-left:2%; margin-right: 40px">Clínica DevWeb&nbsp<img src="img/health.png" alt="aaaa" style="width:30px;height:30px;"></a>
            
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroPlano.jsp">Cadastrar plano</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="AdministradorController?acao=AdmIncluir">Cadastrar administrador</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="MedicoController?acao=AdmIncluir">Cadastrar médico</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="clinicaEspecialidade.jsp">Cadastrar especialidade</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroTipoExame.jsp">Cadastrar Tipo Exame</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="cadastroPlano.jsp">Cadastrar Tipo Plano</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" style="margin-right:30px;" href="PacienteController?acao=AdmIncluir">Cadastrar Paciente</a>
                                </li>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item">
                            <a class="nav-link" style="margin-right:30px;" href="index.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
    </body>
</html>
