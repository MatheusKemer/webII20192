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

<nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Web 2</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="${(pagina == null || pagina.endsWith('/AtendimentoServlet')) ? 'active' : ''}"><a href="Portal.jsp">Atendimentos</a></li>
        <li class="${pagina.endsWith('/ClientesServlet') ? 'active' : ''}"><a href="ClientesServlet">Cadastro de Clientes</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a>Olá, <c:out value="${LoginBean.name}" /></a></li>
        <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
      </ul>
    </div>
</nav> 