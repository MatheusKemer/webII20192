<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Proutos | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="js/libs/DataTables/dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_funcionario.jsp"/>
            
            <div class="form-holder">
                <h1>Produtos</h1>
                <table cellpadding=0 cellspacing=0>
                    <thead>
                    <th>ID</th>
                    <th>Categoria</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Peso</th>
                    <th>Ver</th>
                    <th>Excluir</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listagemProdutos}" var="produto" >
                            <tr>
                                <td><c:out value="${produto.id}" /></td>
                                <td id="type${produto.id}"><c:out value="${produto.categoria.nome}" /></td>
                                <td id="title${produto.id}"><c:out value="${produto.nome}" /></td>
                                <td id="desc${produto.id}"><c:out value="${produto.descricao}" /></td>
                                <td id="peso${produto.id}"><c:out value="${produto.peso}" /></td>
                                <td><button class="prodView">Ver</button></td>
                                <td class="delete prod">X</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="modal hidden">
                    <div class="modal-body hidden">
                        <h2 id="title"></h2>
                        <form action="ProdutoServlet?action=update" method="POST">
                            <input name="id" type="text" class="hidden" id="prodID"
                            <p>Titulo: <br /><span id="title"></span></p>
                            <input name="nome" type="text" class="hidden prodChange" placeholder="Novo Título">
                            <p>Categoria: <br /><span id="type"></span></p>
                            <select class="hidden prodChange" name="categoriaId" >
                                <c:forEach items="${categorias}" var="categoria">
                                    <option value="<c:out value="${categoria.id}" />" <c:if test="${categoria.id == produto.categoria.id}"><c:out value="selected=true" /></c:if> >
                                        <c:out value="${categoria.nome}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <p>Descrição: <br /><span id="desc"></span></p>
                            <textarea name="description" id="desc" class="hidden prodChange" placeholder="Nova Descrição"></textarea>
                            <p>Peso: <br /><span id="peso"></span></p>
                            <input name="peso" id="peso" class="hidden prodChange"type="text" id="weightIn" placeholder="Novo Peso">
                            <button type="submit" class="prodChange hidden">Alterar</button>
                            <button class="prodEdit">Editar</button>
                            <button class="prodCancel hidden">Cancelar</button>
                            <button class="prod-close">Fechar</button>
                        </form>
                        
                    </div>
                    <div class="modal-delete hidden">
                        <h2>Deseja mesmo excluir este item?</h2>
                        <button class="delete-cancel">Cancelar</button>
                        <form action="ProdutoServlet?action=remove&id=" method="POST">
                            <input class="hidden" id="delete-id">
                            <button type="submit">Excluir</button>
                        </form>
                    </div>
                </div>
                <div class="hidden">
                    <div class="table-data">
                        <div id="20">
                            <p id="title20">Creme de espinhas</p>
                            <p id="type20">Pele</p>
                            <p id="desc20">Creme para o tratamento de espinhas  </p>
                            <p id="peso20">20g</p>
                        </div>
                    </div>
                </div>
                <a href="ProdutoServlet?action=formNew">Criar Produto</a>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
