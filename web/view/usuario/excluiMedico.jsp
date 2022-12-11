<%-- 
    Document   : cadastroMedico
    Created on : 09/10/2022, 18:54:30
    Author     : Ferraz-PC
--%>

<%@page import="aplicacao.Especialidade"%>
<%@page import="aplicacao.Medico"%>
<%@page import="dao.medicoDAO"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <!--link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"-->
        <title>Cadastrar médico</title>
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            medicoDAO medicoDAO = new medicoDAO();
            Medico medico = medicoDAO.getMedico(id);
            Especialidade especialidade = medicoDAO.getEspecialidade(id);
            %>
        <h1 class="titulo-login">Cadastrar médico</h1>
        <div class="container text-center">
                <div class="row mt-5">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <div class="row">
                            <form class="form-group" action="MedicoController?acao=Excluir" method="POST">
                                <input type="hidden" name="id" value="<%=medico.getId()%>" />
                                <div class="col-12">
                                    <input type="text" name="nome" Readonly placeholder="Nome" value="<%=medico.getNome()%>" class=" form-control mb-3" />
                                </div>
                                <div class="col-12">
                                    <input type="text" name="cpf" Readonly placeholder="CPF" value="<%=medico.getCpf()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="number" name="crm" Readonly placeholder="CRM" value="<%=medico.getCrm()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="estadocrm" Readonly placeholder="Estado CRM" value="<%=medico.getEstadoCrm()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="autorizado" Readonly placeholder="Autorizado" value="<%=medico.getAutorizado()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">
                                    <input type="text" name="especialidade" Readonly placeholder="Especialidade" value="<%=medico.getIdEspecialidade()%>" class="form-control mb-3"/>
                                </div>
                                <div class="col-12">  
                                    <input type="password" name="senha" Readonly placeholder="Senha" value="<%=medico.getSenha()%>" class="form-control mb-3"/>
                                </div>  
                                <button type="submit" class="btn btn-primary mt-1" style="background-color: #6610f2">Excluir</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>
