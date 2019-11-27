<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Dados | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body>
        <div class="content">
            <c:choose>
                <c:when test="${usuario.getTipo() == \"Gerente\"}">
                   <c:import url="/cabecalho_gerente.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:import url="/cabecalho_cliente.jsp"/>
                </c:otherwise>
            </c:choose>
            <c:set var="cliente" value="${cliente}" />
            
            <div class="form-holder">
                <c:import url="/usuarioForm.jsp"/>
            </div>
        </div>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
