<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <c:import url="/cabecalho_gerente.jsp"/>
            
            <div class="form-holder">
                <h1>Bem vindo!</h1>
                <h2>Atendimentos criados: <span><c:out value="${atendimentosTotais}" /></span></h2>
                <h2>Atendimentos abertos: <span><c:out value="${atendimentosAbertos}" /></span> - <span><c:out value="${(atendimentosAbertos/atendimentosTotais)*100}" />%</span></h2>
                <table cellpadding=0 cellspacing=0 class="table-manager">
                    <thead>
                        <th>Tipo</th>
                        <th>Abertos/Totais</th>                    
                    </thead>
                    <tbody>
                        <tr>
                            <td>Críticas</td>
                            <td><c:out value="${criticasAbertas}" />/<c:out value="${criticas}" /></td>
                        </tr>
                        <tr>
                            <td>Reclamações</td>
                            <td><c:out value="${reclamacoesAbertas}" />/<c:out value="${reclamacoes}" /></td>
                        </tr>
                        <tr>
                            <td>Dúvidas</td>
                            <td><c:out value="${duvidasAbertas}" />/<c:out value="${duvidas}" /></td>
                        </tr>
                        <tr>
                            <td>Sugestões</td>
                            <td><c:out value="${sugestoesAbertas}" />/<c:out value="${sugestoes}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
