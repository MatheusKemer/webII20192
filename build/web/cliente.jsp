<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="js/libs/DataTables/dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">        
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_cliente.jsp"/>
            <div class="form-holder">
                <h1>Atendimentos</h1>
                <table cellpadding=0 cellspacing=0>
                    <thead>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Produto</th>
                    <th>Status</th>
                    <th>Data de Criação</th>
                    <th>Ver</th>
                    <th>Excluir</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>20</td>
                            <td>Critica</td>
                            <td>Creme</td>
                            <td>Concluído</td>
                            <td>12/09/2019</td>
                            <td><button class="questionView">Ver</button></td>
                            <td class="disabled">X</td>
                        </tr>
                        <tr>
                            <td>25</td>
                            <td>Critica</td>
                            <td>Creme</td>
                            <td>Aberto</td>
                            <td>20/09/2019</td>
                            <td><button class="questionView">Ver</button></td>
                            <td class="delete">X</td>
                        </tr>
                        <tr>
                            <td>28</td>
                            <td>Critica</td>
                            <td>Creme</td>
                            <td>Aberto</td>
                            <td>22/09/2019</td>
                            <td><button class="questionView">Ver</button></td>
                            <td class="delete">X</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal hidden">
                <div class="modal-body hidden">
                    <h2 id="title"></h2>
                    <p>Status: <span id="status"></span></p>
                    <p>Aberto em: <span id="openDate"></span></p>
                    <p>Concluído em: <span id="closeDate"></span></p>
                    <p>Produto: <span id="product"></span></p>
                    <p>Solicitação: <br /><span id="question"></span></p>
                    <p>Resposta do atendimento: <br /><span id="answer"></span></p>
                    <button class="modal-close">Fechar</button>
                </div>
                <div class="modal-delete hidden">
                    <h2>Deseja mesmo excluir este item?</h2>
                    <button class="delete-cancel">Cancelar</button>
                    <form>
                        <input type="text" class="hidden" id="delete-id">
                        <button type="submit">Excluir</button>
                    </form>
                </div>
            </div>
            <div class="hidden">
                <div class="table-data">
                    <div id="20">
                        <p id="title20">Crítica</p>
                        <p id="status20">Concluído</p>
                        <p id="openDate20">12/09/2019</p>
                        <p id="closeDate20">14/09/2019</p>
                        <p id="product20">Batão</p>
                        <p id="question20">Estou usando o creme há  um mês e a espinha só aumenta.</p>
                        <p id="answer20">Vá ao médico, pode ser câncer!</p>
                    </div>  
                </div>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
