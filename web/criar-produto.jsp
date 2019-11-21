<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Criar Produto | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_funcionario.jsp"/>
            
            <div class="form-holder">
                <h1>Criar Produto</h1>
                <form action="<c:out value="${action}" />" method="POST">
                    <select name="categoriaId">
                        <c:forEach items="${categorias}" var="categoria">
                            <option value="<c:out value="${categoria.id}" />" <c:if test="${categoria.id == produto.categoria.id}"><c:out value="selected=true" /></c:if> >
                                <c:out value="${categoria.nome}" />
                            </option>
                        </c:forEach>
                    </select>
                    <input name="nome" type="text" placeholder="Título">
                    <input name="peso" type="text" id="weightIn" placeholder="Peso">
                    <textarea name="description" rows="20" cols="53" type="textarea" placeholder="Escreva uma breve descrição"></textarea>
                    <button type="submit">Criar</button>
                </form>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
