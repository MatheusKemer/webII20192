<%-- 
    Document   : Portal
    Created on : 19/09/2019, 15:19:59
    Author     : matheus
--%>

<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp"/>
        
        <div class="container">
            <div class="col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                <a class="btn btn-success pull-left" href="AtendimentoServlet?action=index"> Mostrar Atendimentos </a>
                <a class="btn btn-success pull-right" href="AtendimentoServlet?action=formNew"> Efetuar Atendimento </a>
            </div>
        </div>
        <br /><br />
        <div class="container">
            <div class="col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                <form action="RelatorioTipoAtendimento" target="_blank">
                    <select name="tipo">
                        <option value="finalizado"> Finalizado </option>
                        <option value="naofinalizado"> Não Finalizado </option>
                    </select>

                    <button class="btn btn-success" target="_blank"> Gerar </button>
                </form>
            </div>
        </div>
        
        <div class="footer navbar-fixed-bottom" style="position:absolute;bottom:0;width:100%">
            <div class="container text-center">
                <small>
                    <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
                    Em caso de problemas contactar o administrador: <c:out value="${configuracao.email}" />
                </small>
            </div>
        </div>
        
        <script src="bootstrap.js" />
    </body>
</html>
