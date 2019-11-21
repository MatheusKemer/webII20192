<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Criar Atendimento | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_cliente.jsp"/>
            
            <div class="form-holder">
                <h1>Criar Atendimento</h1>
                <form action="AtendimentoServlet?action=new" method="POST">
                    <select name="tipoAtendimentoId">
                        <c:forEach items="${tiposAtendimento}" var="tipoAtendimento">
                            <option value="<c:out value="${tipoAtendimento.id}" />" <c:if test="${tipoAtendimento.id == atendimento.tipoAtendimento.id}"><c:out value="selected=true" /></c:if> >
                                <c:out value="${tipoAtendimento.nome}" />
                            </option>
                        </c:forEach>
                    </select>
                    <select name="produtoId">
                        <c:forEach items="${produtos}" var="produto">
                            <option value="<c:out value="${produto.id}" />" <c:if test="${produto.id == atendimento.produto.id}"><c:out value="selected=true" /></c:if> >
                                <c:out value="${produto.nome}" />
                            </option>
                        </c:forEach>
                    </select>
                    <textarea name="descricao" rows="20" cols="53" type="textarea" placeholder="Escreva uma breve descrição"></textarea>
                    <button type="submit">Criar</button>
                </form>
            </div>
        </div>
    </body>
</html>
