<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Usuário | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body>
        <div class="content">
            <header class="gerente-header">
                <a href="gerente.jsp">Home</a>
                <a href="gerente.jsp">Atendimentos</a>
                <a href="relatorios.jsp">Relatórios</a>
                <a href="/Telas">Logout</a>
            </header>
            <div class="form-holder">
                <h1>Alterar Dados</h1>
                <form action=''>
                    <select>
                        <option>Cliente</option>
                        <option>Funcionário</option>
                        <option>Gerente</option>
                    </select>
                    <input type="text" placeholder="Nome">
                    <input type="text" placeholder="CPF">
                    <input type="text" placeholder="Email">
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
