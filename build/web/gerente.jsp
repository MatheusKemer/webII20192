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
                <h2>Atendimentos criados: <span>400</span></h2>
                <h2>Atendimentos abertos: <span>200</span> - <span>50%</span></h2>
                <table cellpadding=0 cellspacing=0 class="table-manager">
                    <thead>
                        <th>Tipo</th>
                        <th>Abertos/Totais</th>                    
                    </thead>
                    <tbody>
                        <tr>
                            <td>Críticas</td>
                            <td>100/200</td>
                        </tr>
                        <tr>
                            <td>Elogios</td>
                            <td>2/10</td>
                        </tr>
                        <tr>
                            <td>Dúvidas</td>
                            <td>78/90</td>
                        </tr>
                        <tr>
                            <td>Sugestões</td>
                            <td>20/110</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
