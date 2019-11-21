<%-- 
    Document   : header
    Created on : 23/10/2019, 15:56:27
    Author     : matheus
--%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />

<c:set var="LoginBean" value="${sessionScope.LoginBean}" />
<c:choose>
     <c:when test="${LoginBean == null}">
        <c:redirect url="/index.jsp">
            <c:param name="msg" value="Usuário deve se autenticar para acessar o sistema!"/>
        </c:redirect>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<header class="gerente-header">
    <a href="gerente.jsp">Home</a>
    <a href="GerenteServlet">Atendimentos</a>
    <a href="relatorios.jsp">Relatórios</a>
    <a href="LogoutServlet">Logout</a>
</header>