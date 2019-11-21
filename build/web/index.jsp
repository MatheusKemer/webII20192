<%@page errorPage="erro.jsp" %>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Serviço de Atendimento ao Cliente | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/libs/JQuery/jquery.min.js"></script>
    </head>
    <body class="home">
        <div class="container">
            <% 
                String alert = "";
                alert = (String)request.getAttribute("msg"); 
                if (alert != null) {
                    out.println("<div class=\"alert alert-secondary\" role=\"alert\">" + alert + "</div>");    
                } else {
                    alert = (String)request.getParameter("msg");
                    if (alert != null) {
                        out.println("<div class=\"alert alert-secondary\" role=\"alert\">" + alert + "</div>");
                    }
                }
            %>
            <div class="container-margin">
                <div class="container">
                    <div class="container-box">
                        <h1>BEIBE</h1>
                        <h1>SAC Online</h1>
                        <div class="button-box">
                            <a href="UsuariosServlet?action=formNew" class="container-box-button">Cadastrar</a>
                            <a href="LoginServlet" class="container-box-button">Já sou cadastrado</a>
                        </div>  
                    </div>
                </div>
            </div>
            <div class="col-8 mx-auto text-center">
                <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
                Em caso de problemas contactar o administrador: <c:out value="${configuracao.email}" />
            </div>
        </div>
    </body>
</html>
