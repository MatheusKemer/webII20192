<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuários | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="js/libs/DataTables/dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_gerente.jsp"/>
            
            <div class="form-holder">
                <h1>Usuários</h1>
                <table cellpadding=0 cellspacing=0>
                    <thead>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>CPF</th>
                    <th>Acesso</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listagemUsuarios}" var="usuario" >
                            <tr>
                                <td><c:out value="${usuario.id}" /></td>
                                <td id="type${usuario.id}"><c:out value="${usuario.nome}" /></td>
                                <td id="title${usuario.id}"><c:out value="${usuario.email}" /></td>
                                <td id="cpf${usuario.id}"><c:out value="${usuario.cpf}" /></td>
                                <td id="peso${usuario.id}"><c:out value="${usuario.tipo}" /></td>
                                <td><a class="none" href="UsuariosServlet?action=formUpdate&id=${usuario.id}" class="userEdit">Editar</a></td>
                                <td class='<c:if test="${currentUser.id != usuario.id}"><c:out value="delete user" /></c:if>'>X</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="UsuariosServlet?action=formNew">Novo Usuário</a>
                <div class="modal-delete hidden">
                    <h2>Deseja mesmo excluir este usuário?</h2>
                    <button class="delete-cancel">Cancelar</button>
                    <form method="POST">
                        <input class="hidden" id="delete-id">
                        <button type="submit">Excluir</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
