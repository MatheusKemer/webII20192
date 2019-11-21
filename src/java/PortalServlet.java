/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.UsuarioDAO;
import beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Herik
 */
@WebServlet(urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String nome = (String)session.getAttribute("nome");
        
        if (nome == null || nome.isEmpty()){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErroServlet");
            request.setAttribute("msg", "Faça Login para continuar");
            request.setAttribute("page", "index.html");
            rd.forward(request, response);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PortalServlet</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"bootstrap.css\" />"); 
            out.println("</head>");
            out.println("<body>");
                out.println("<div class=\"container\"><div class=\"col-12 text-center\"><h4>Bem Vindo " + nome + "!</h4></div>");
                out.println("<form action=\"CadastrarUsuarioServlet\" method=\"POST\">");
                    out.println("<div class=\"form-group col-6 col-sm-3 mx-auto\">");
                        out.println("<label for=\"nome\">Nome: </label>");
                        out.println("<input type=\"nome\" name=\"nome\" class=\"form-control\"/>");
                    out.println("</div>");
                    out.println("<div class=\"form-group col-6 col-sm-3 mx-auto\">");
                        out.println("<label for=\"login\">Login: </label>");
                        out.println("<input type=\"text\" name=\"login\" class=\"form-control\"/>");
                    out.println("</div>");
                    out.println("<div class=\"form-group col-6 col-sm-3 mx-auto\">");
                        out.println("<label for=\"senha\">Senha: </label>");
                        out.println("<input type=\"password\" name=\"senha\" class=\"form-control\"/>");
                    out.println("</div>");
                    out.println("<div class=\"col-12 text-center\">");
                        out.println("<button type=\"submit\" class=\"btn btn-success\">Salvar</button>");
                    out.println("</div>");
                out.println("</form>");
            
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> usuariosCadastrados = dao.buscarTodos();
            out.println("<br />");
            out.println("<table class=\"table table-striped col-6 mx-auto\"");
                out.println("<tr>");
                   out.println("<th>ID</th>");
                   out.println("<th>Nome</th>");
                   out.println("<th>Login</th>");
                   out.println("<th>Senha</th>");
                out.println("</tr>");
                
            for(int i=0;i<usuariosCadastrados.size();i++){
                out.println("<tr>");
                out.println("<td>" + usuariosCadastrados.get(i).getId() + "</td>");
                out.println("<td>" + usuariosCadastrados.get(i).getNome() + "</td>");
                out.println("<td>" + usuariosCadastrados.get(i).getLogin() + "</td>");
                out.println("<td>" + usuariosCadastrados.get(i).getSenha() + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            out.println("<div class=\"text-center\"><a class=\"btn btn-danger\" href=\"LogoutServlet\">Sair</a></div></div>");
            out.println("<script src=\"bootstrap.js\" />");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
