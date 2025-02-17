<%@page import="beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login | BEIBE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
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
            
            if (session.getAttribute("usuario") != null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/AtendimentoServlet");
                request.setAttribute("msg", "Você já está logado!");
                rd.forward(request, response);
            }
            
        %>
        <div class="content">
            <div class="form-holder">
                <h1>Login</h1>
                <form action="LoginServlet" method="POST">
                    <input type="text" name="email" id="email" placeholder="Email">
                    <input type="password" name="senha" placeholder="Senha">
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
        <script src="js/libs/JQuery/jquery.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
