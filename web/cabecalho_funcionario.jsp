<%-- 
    Document   : header
    Created on : 23/10/2019, 15:56:27
    Author     : matheus
--%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="currentUser" value="${sessionScope.usuario}" scope="session" />
<c:set var="LoginBean" value="${sessionScope.LoginBean}" />
<c:choose>
     <c:when test="${LoginBean == null}">
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<header class="funcionario-header">
    <a href="AtendimentoServlet">Atendimentos</a>
    <a href="CategoriaServlet">Categorias</a>
    <a href="ProdutoServlet">Produtos</a>
    <a href="LogoutServlet">Logout</a>
</header>

<% 
    String alert = "";
    alert = (String)request.getAttribute("msg"); 
    if (alert != null) {
        out.println("<div class=\"alert alert-secondary\" role=\"alert\">" + alert + "</div>");    
    } else {
        alert = (String)request.getParameter("msg");
        if (alert != null) {
            out.println("<div class=\"alert alert-secondary\" role=\"alert\">" + alert + "</div>");
        }
    }
%>