<%-- 
    Document   : clienteListar
    Created on : 02/10/2019, 21:19:48
    Author     : matheus
--%>

<%@page import="beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Clientes</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        <div class="container">
            <table class="table table-striped col-sm-4">
                <a href="GeradorRelatorio" class="btn btn-primary pull-right">Relatório</a>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Email</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listagemClientes}" var="cliente" >
                        <tr>
                            <td><c:out value="${cliente.id}" /></td>
                            <td><c:out value="${cliente.nome}" /></td>
                            <td><c:out value="${cliente.cpf}" /></td>
                            <td><c:out value="${cliente.email}" /></td>
                            <td>
                                <a href="ClientesServlet?action=show&id=${cliente.id}"><span class="glyphicon glyphicon-eye-open"></span></a>&nbsp;&nbsp;&nbsp;
                                <a href="ClientesServlet?action=formUpdate&id=${cliente.id}"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;&nbsp;
                                <a href="ClientesServlet?action=remove&id=${cliente.id}" onclick="return confirm('Você tem certeza?');"><span class="glyphicon glyphicon-trash"></span></a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <br />
        <div class="col-xs-12 text-center">
            <a class="btn btn-success" href="ClientesServlet?action=formNew"> Novo Cliente </a>
        </div>
    </body>
</html>
