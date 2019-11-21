<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Dados | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body>
        <div class="content">
            <header class="cliente-header">
                <a href="cliente.jsp">Home</a>
                <a href="novo-atendimento.jsp">Criar Atendimento</a>
                <a href="/Telas">Logout</a>
            </header>
            <div class="form-holder">
                <h1>Alterar Dados</h1>
                <form action=''>
                    <input type="text" placeholder="Nome">
                    <input type="text" disabled placeholder="CPF">
                    <input type="text" disabled placeholder="Email">
                    <input type="text" placeholder="Endereço">
                    <input type="text" id="housenumber" placeholder="Nº">
                    <input type="text" placeholder="Comp">
                    <input type="text" placeholder="Bairro">
                    <input type="text" id="cep" placeholder="CEP">
                    <input type="text" placeholder="Cidade">
                    <input type="text" placeholder="UF">
                    <input type="phone" id="phone" placeholder="Telefone">
                    <input type="password" id="pass" placeholder="Senha">
                    <input type="password" id="cpass" placeholder="Confirmar Senha">
                    <button type="submit">Alterar</button>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
