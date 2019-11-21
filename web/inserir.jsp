<%-- 
    Document   : inserir
    Created on : 19/09/2019, 15:59:13
    Author     : matheus
--%>

<%@page import="beans.LoginBean"%>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Usuário</title>
        <link rel="stylesheet" href="bootstrap.css" />
    </head>
    <body>
        <% 
            LoginBean bean = (LoginBean)session.getAttribute("LoginBean"); 
            if (bean == null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Faça login para continuar!");
                request.setAttribute("page", "index.html");
                rd.forward(request, response);
            } 
        %>
        
        <form action="CadastrarUsuarioServlet" method="POST">
            <div class="form-group col-6 col-sm-3 mx-auto">
                <label for="nome">Nome: </label>
                <input type="text" name="nome" class="form-control"/>
            </div>
            <div class="form-group col-6 col-sm-3 mx-auto">
                <label for="nome">Login: </label>
                <input type="text" name="login" class="form-control"/>
            </div>
            <div class="form-group col-6 col-sm-3 mx-auto">
                <label for="nome">Senha </label>
                <input type="password" name="senha" class="form-control"/>
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-success">Salvar</button>
            </div>
        </form>
    </body>
</html>
