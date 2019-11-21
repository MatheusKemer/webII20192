/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.UsuarioDAO;
import beans.Usuario;
import beans.LoginBean;
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
@WebServlet(urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cadastro de Usuário</title>");   
            out.println("<link rel=\"stylesheet\" href=\"bootstrap.css\" />"); 
            out.println("</head>");
            out.println("<body>");
           
            HttpSession session = request.getSession();
            LoginBean bean = (LoginBean)session.getAttribute("LoginBean"); 
            
            if (bean == null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Faça login para continuar!");
                request.setAttribute("page", "index.html");
                rd.forward(request, response);
            }
            
            String nome = request.getParameter("nome");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            
            if (("".equals(nome)) || ("".equals(login)) || ("".equals(senha))){
                out.println("<div class=\"col-8 mx-auto alert alert-danger\" role=\"alert\">\n" +
                            "Informe todos os dados do cadastro!\n" +
                            "</div>");
            }
            else {
            
                Usuario usuario = new Usuario(nome, login, senha);
                UsuarioDAO dao = new UsuarioDAO();

                dao.inserir(usuario);

                out.println("<div class=\"col-8 mx-auto alert alert-success\" role=\"alert\">\n" +
                        "<p> Usuário cadastrado com Sucesso!</p>" +
                        "</div>");
            }
            out.println("<div class=\"col-12 text-center\"><a class=\"btn btn-primary\" href=\"Portal.jsp\">Continuar</a></div>");
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
