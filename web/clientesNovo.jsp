<%-- 
    Document   : clientesNovo
    Created on : 03/10/2019, 13:32:52
    Author     : matheus
--%>

<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Cliente</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        
        <div class="container">
            <div class="col-xs-6 col-xs-offset-3">
                <form action="NovoClienteServlet" method="post">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" name="nome" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>CPF</label>
                        <input type="text" name="cpf" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Data</label>
                        <input type="date" name="data" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>CEP</label>
                        <input type="number" name="cep" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Rua</label>
                        <input type="text" name="rua" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Número</label>
                        <input type="numver" name="numero" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>UF</label>
                        <input type="text" name="uf" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label>Cidade</label>
                        <input type="text" name="cidade" class="form-control" />
                    </div>

                    <br />

                    <div class="">
                        <a href="ClientesServlet" class="btn btn-danger pull-left"> Voltar </a>
                        <button type="submit" class="btn btn-success pull-right"> Criar </button>
                    </div>
                </form>
            </div>        
        </div>
    </body>
</html>
