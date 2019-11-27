<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Atendimentos | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="js/libs/DataTables/dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">        
    </head>
    <body>
        <div class="content">
            <c:choose>
                <c:when test="${usuario.getTipo() == \"Gerente\"}">
                   <c:import url="/cabecalho_gerente.jsp"/>
                </c:when>
                <c:when test="${usuario.getTipo() == \"Funcionario\"}">
                   <c:import url="/cabecalho_funcionario.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:import url="/cabecalho_cliente.jsp"/>
                </c:otherwise>
            </c:choose>
            <div class="form-holder">
                <h1>Atendimentos</h1>
                <c:if test="${(usuario.getTipo() == \"Gerente\") || (usuario.getTipo() == \"Funcionario\")}">
                    <a href="AtendimentoServlet?tipo=abertos"> Abertos </a>
                    <a href="AtendimentoServlet?tipo=todos"> Todos </a>
                </c:if>
                
                <table cellpadding=0 cellspacing=0>
                    <thead>
                        <th>ID</th>
                        <th>Tipo</th>
                        <th>Status</th>
                        <th>Data de Criação</th>
                        <th>Ver</th>
                        <c:if test="${(usuario.getTipo() == \"Cliente\")}">
                            <th>Excluir</th>
                        </c:if>
                    </thead>
                    <tbody>
                        <c:forEach items="${listagemAtendimentos}" var="atendimento" >
                            <span class="hidden" id="question${atendimento.id}"><c:out value="${atendimento.desc}" /></span>
                            <span class="hidden" id="product${atendimento.id}"><c:out value="${atendimento.produto.nome}" /></span>
                            <span class="hidden" id="answer${atendimento.id}"><c:out value="${atendimento.resposta}" /></span>
                        
                            <tr>
                                <td><c:out value="${atendimento.id}" /></td>
                                <td id="title${atendimento.id}"><c:out value="${atendimento.tipoAtendimento.nome}" /></td>
                                <td class="questStat" id="status${atendimento.id}"><c:out value="${atendimento.status}" /></td>
                                <td class="questDate" id="openDate${atendimento.id}"><fmt:formatDate value="${atendimento.data}" pattern="dd/MM/yyyy" /></td>
                                <td><button class="questionView">Ver</button></td>
                                <c:if test="${(usuario.getTipo() == \"Cliente\")}">
                                    <td class="delete status">X</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${usuario.getTipo() == \"Cliente\"}">
                    <a href="AtendimentoServlet?action=formNew" class="text-center"> Novo Atendimento </a>
                </c:if>
            </div>
            <div class="modal hidden">
                <div class="modal-body hidden">
                    <h2 id="title"></h2>
                    <p>Status: <span id="status"></span></p>
                    <p>Produto: <span id="product"></span></p>
                    <p>Aberto em: <span id="openDate"></span></p>
                    <p>Concluído em: <span id="closeDate"></span></p>
                    <p>Solicitação: <br /><span id="question"></span></p>
                    <p>Resposta do atendimento: <br /><span id="answer"></span></p>
                    <c:if test="${(usuario.getTipo() == \"Funcionario\")}">
                        <div class="questReply">
                            <form method="POST">
                                <input class="hidden" id="atendimento-id" />
                                <input name="reply" class="" required />
                                <button type="submit"> Responder </button>
                            </form>
                        </div>
                    </c:if>
                    <button class="modal-close">Fechar</button>
                </div>
            </div>
            <div class="modal-delete hidden">
                <h2>Deseja mesmo excluir este atendimento?</h2>
                <button class="delete-cancel">Cancelar</button>
                <form action="AtendimentoServlet?action=remove&id=" method="POST">
                    <input class="hidden" id="delete-id">
                    <button type="submit">Excluir</button>
                </form>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script src="js/libs/DataTables/dataTables.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
