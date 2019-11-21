<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <header class="cliente-header">
                <a href="cliente.jsp">Home</a>
                <a href="alterar-dados.jsp">Alterar Dados</a>
                <a href="/Telas">Logout</a>
            </header>
            <div class="form-holder">
                <h1>Criar Atendimento</h1>
                <form>
                    <select>
                        <option>Tipo do atendimento</option>
                    </select>
                    <select>
                        <option>Produto</option>
                    </select>
                    <textarea rows="20" cols="53" type="textarea" placeholder="Escreva uma breve descrição"></textarea>
                    <button type="submit">Criar</button>
                </form>
            </div>
        </div>
    </body>
</html>
