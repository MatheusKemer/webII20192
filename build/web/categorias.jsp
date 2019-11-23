<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Categorias | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="js/libs/DataTables/dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_funcionario.jsp"/>
            
            <div class="form-holder">
                <h1>Categorias</h1>
                <table cellpadding=0 cellspacing=0>
                    <thead>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Ver</th>
                        <th>Excluir</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listagemCategorias}" var="categoria" >
                            <tr>
                                <td><c:out value="${categoria.id}" /></td>
                                <td id="title${categoria.id}"><c:out value="${categoria.nome}" /></td>
                                <td><button class="catView">Ver</button></td>
                                <td class="delete cat">X</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="modal hidden">
                    <div class="modal-body hidden">
                        <h2 id="title"></h2>
                        <form action="CategoriaServlet?action=update" method="POST">
                            <input name="id" type="text" class="hidden" id="catID">
                            <input name="nome" type="text" class="hidden catChange" placeholder="Novo Título">
                            <button type="submit" class="catChange hidden">Alterar</button>
                            <button class="catEdit">Editar</button>
                            <button class="catCancel hidden">Cancelar</button>
                            <button class="cat-close">Fechar</button>
                        </form>
                        
                    </div>
                    <div class="modal-delete hidden">
                        <h2>Deseja mesmo excluir este item?</h2>
                        <button class="delete-cancel">Cancelar</button>
                        <form method="POST">
                            <input class="hidden" id="delete-id">
                            <button type="submit">Excluir</button>
                        </form>
                    </div>
                </div>
                <a href="CategoriaServlet?action=formNew">Criar Categoria</a>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
