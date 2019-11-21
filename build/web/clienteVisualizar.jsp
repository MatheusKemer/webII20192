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
        <title>Detalhes Cliente</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <jsp:useBean id="cliente" class="beans.Cliente" scope="request"/>
        
        <c:import url="/header.jsp" />
        
        <div class="container">
            <div class="col-sm-10">
                <label class="text-right">Nome:</label>
                <c:out value="${cliente.nome}" />
                
                <br />
                
                <label class="text-right">Email:</label>
                <c:out value="${cliente.email}" />

                <br />

                <label class="text-right">CPF:</label>
                <c:out value="${cliente.cpf}" />

                <br />

                <label class="text-right">Data:</label>
                <fmt:formatDate value="${cliente.data}" pattern="dd/MM/yyyy" />

                <br />

                <label class="text-right">Endereço:</label>
                <c:out value="${cliente.rua}" />, <c:out value="${cliente.numero}" /> - <c:out value="${cliente.cep}" />, <c:out value="${cliente.cidade.nome}" />, <c:out value="${cliente.cidade.estado.nome}" />
                <br />
                
                <a href="ClientesServlet" class="btn btn-danger"> Voltar </a>
                <a href="ClientesServlet?action=formUpdate&id=${cliente.id}" class="btn btn-primary"> Editar </a>
                
            </div>
        </div>
                
    </body>
</html>
