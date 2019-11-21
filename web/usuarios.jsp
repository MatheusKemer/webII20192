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
            <c:import url="/cabecalho_cliente.jsp"/>
            
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
                        <tr>
                            <td>01</td>
                            <td>João</td>
                            <td>jonnes@gmail.com</td>
                            <td>657.404.620-37</td>
                            <td>Gerente</td>
                            <td><button class="userEdit">Editar</button></td>
                            <td class="disabled">X</td>
                        </tr>
                        <tr>
                            <td>02</td>
                            <td>Maria</td>
                            <td>mary_jane@gmail.com</td>
                            <td>663.634.200-59</td>
                            <td>Funcionário</td>
                            <td><button class="userEdit">Editar</button></td>
                            <td class="delete">X</td>
                        </tr>
                        <tr>
                            <td>03</td>
                            <td>Larissa</td>
                            <td>larissinha@gmail.com</td>
                            <td>860.765.530-22</td>
                            <td>Cliente</td>
                            <td><button class="userEdit">Editar</button></td>
                            <td class="delete">X</td>
                        </tr>
                    </tbody>
                </table>
                <form id="edit-user-form" class="hidden">
                    <input type="text" id="edit-user-id">
                    <input type="submit" id="edit-user-go">
                </form>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
