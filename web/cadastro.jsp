<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="content">
            <div class="form-holder">
                <h1>Cadastro</h1>
                <form action="UsuariosServlet?action=new" method="POST">
                    <input name="nome" type="text" placeholder="Nome">
                    <input name="cpf" type="text" id="cpf" placeholder="CPF">
                    <input name="nome" type="email" id="email" placeholder="Email">
                    <input name="rua" type="text" placeholder="Endereço">
                    <input name="numero" class="small" type="text" id="housenumber" placeholder="Nº">
                    <input name="complemento" class="medium" type="text" placeholder="Comp">
                    <input name="bairro" type="text" placeholder="Bairro">
                    <input name="cep" type="text" id="cep" placeholder="CEP">
                    <input name="cidadeId" class="medium" type="text" placeholder="Cidade">
                    <input name="estadoId" class="small" type="text" placeholder="UF">
                    <input name="telefone" type="phone" id="phone" placeholder="Telefone">
                    <input name="senha" type="password" id="pass" placeholder="Senha">
                    <input name="confirmacao_senha" type="password" id="cpass" placeholder="Confirmar Senha">
                    <button type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
