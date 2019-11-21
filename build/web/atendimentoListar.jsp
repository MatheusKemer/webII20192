<%-- 
    Document   : atendimentoListar
    Created on : 11/14/2019, 21:19:48
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
        <title>Listagem de Atendimentos</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        
        <div class="container">
            <table class="table table-striped col-sm-4">
                <a href="RelatorioAtendimento" class="btn btn-primary pull-right">Relatório</a>
                <thead>
                    <tr>
                        <th>Nome do Cliente</th>
                        <th>Data</th>
                        <th>Produto</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listagemAtendimentos}" var="atendimento" >
                        <tr>
                            <td><c:out value="${atendimento.cliente.nome}" /></td>
                            <td><c:out value="${atendimento.data}" /></td>
                            <td><c:out value="${atendimento.produto.nome}" /></td>
                            <td>
                                <a href="AtendimentoServlet?action=show&id=${atendimento.id}"><span class="glyphicon glyphicon-eye-open"></span></a>&nbsp;&nbsp;&nbsp;
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <br />
        <div class="col-xs-12 text-center">
            <a class="btn btn-success" href="AtendimentoServlet?action=formNew"> Novo Atendimento </a>
        </div>
    </body>
</html>
