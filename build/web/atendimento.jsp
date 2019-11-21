<%-- 
    Document   : atendimento
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
        <title>Novo Atendimento</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        
        <div class="container">
            <div class="col-xs-12 col-sm-6 col-sm-offset-3">
                <h2>Novo Atendimento</h2>
                <form action="AtendimentoServlet?action=new" method="post">
                    <div class="form-group">
                        <label>Descrição</label>
                        <input type="text" name="desc" class="form-control" maxlength="100" required>
                    </div>
                    <div class="form-group">
                        <label>Data</label>
                        <input type="date" name="data" class="form-control" maxlength="100" required>
                    </div>
                    <div class="form-group">
                        <label>Produtos</label>
                        <select class="form-control" id="estado" name="produtoId">
                            <c:forEach items="${produtos}" var="produto" >
                                <option value="<c:out value="${produto.id}"/>">
                                    <c:out value="${produto.nome}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Tipos de Atendimento</label>
                        <select class="form-control" id="estado" name="tipoAtendimentoId">
                            <c:forEach items="${tiposAtendimento}" var="tipoAtendimento" >
                                <option value="<c:out value="${tipoAtendimento.id}"/>">
                                    <c:out value="${tipoAtendimento.nome}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Clientes</label>
                        <select class="form-control" id="estado" name="clienteId">
                            <c:forEach items="${clientes}" var="cliente" >
                                <option value="<c:out value="${cliente.id}"/>">
                                    <c:out value="${cliente.nome}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="status"> Finalizado? </input>
                    </div>
                    
                    <button type="submit" class="btn btn-success center-block"> 
                        Criar
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>
