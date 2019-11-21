<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<jsp:useBean id="action" class="String" scope="request" />
<html>
    <head>
        <title>Criar Categoria | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_funcionario.jsp"/>
            
            <div class="form-holder">
                <h1>Criar Categoria</h1>
                <form action="<c:out value="${action}" />" method="POST">
                    <input name="nome" type="text" placeholder="Título">
                    <button type="submit">Criar</button>
                </form>
            </div>
        </div>
    </body>
</html>
