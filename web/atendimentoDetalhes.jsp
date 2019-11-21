<%-- 
    Document   : clienteVisualizar
    Created on : 02/10/2019, 21:57:57
    Author     : matheus
--%>

<%@page import="beans.Cliente"%>
<%@page import="beans.LoginBean"%>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhes Atendimento</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <jsp:useBean id="atendimento" class="beans.Atendimento" scope="request"/>
        
        <c:import url="/header.jsp" />
        
        <div class="container">
            <div class="col-sm-10">
                <label class="text-right">Data:</label>
                <fmt:formatDate value="${atendimento.data}" pattern="dd/MM/yyyy" />

                <br />

                <label class="text-right">Produto</label>
                <c:out value="${atendimento.produto.nome}" />

                <br />

                <label class="text-right">Cliente:</label>
                <c:out value="${atendimento.cliente.nome}" />

                <br />
                
                <label class="text-right">Status:</label>
                
                <c:choose>
                    <c:when test="${atendimento.status == 'N'}">
                        <c:out value="Não Finalizado" />
                    </c:when>
                    
                    <c:otherwise>
                        <c:out value="Finalizado" />
                    </c:otherwise>
                </c:choose>

                <br />
                
                <a href="AtendimentoServlet" class="btn btn-danger"> Voltar </a>
                
            </div>
        </div>
                
    </body>
</html>
