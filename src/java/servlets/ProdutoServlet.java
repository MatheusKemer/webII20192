/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.CategoriaDAO;
import beans.Categoria;
import beans.LoginBean;
import beans.Produto;
import beans.Usuario;
import facade.CategoriaFacade;
import facade.CidadesFacade;
import facade.EstadoFacade;
import facade.ProdutoFacade;
import facade.UsuariosFacade;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author matheus
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

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
        Usuario usuario = (Usuario)session.getAttribute("usuario"); 

        if (usuario == null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            rd.forward(request, response);
            return;
        }
        
        String action = (String)request.getParameter("action");
        String msg = (String)request.getParameter("msg");
        if(msg == null){
            msg = "";
        }
        if(null != action){
            switch (action) {
                case "list":
                    index(msg, request, response);
                    return;
                case "formUpdate":
                    edit(request, response);
                    return;
                case "remove":
                    delete(request, response);
                    return;
                case "update":
                    update(request, response);
                    return;
                case "formNew":
                    newObject(request, response);
                    return;
                case "new":
                    create(request, response);
                    return;
                default:
                    index(msg, request, response);
            }
        } else {
            index(msg, request, response);
        }
    }

    private void index(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtosCadastrados = ProdutoFacade.buscarTodos();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/produtos.jsp");
        request.setAttribute("listagemProdutos", produtosCadastrados);
        request.setAttribute("categorias", CategoriaFacade.buscarTodos());
        rd.forward(request, response);
    }

    private void newObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/criar-produto.jsp");
        request.setAttribute("action", "ProdutoServlet?action=new");
        request.setAttribute("editing", false);
        request.setAttribute("categorias", CategoriaFacade.buscarTodos());
        request.setAttribute("titleLabel", "Novo Produto");
        rd.forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduto = (String) request.getParameter("id");
        String msg = "";
        if (idProduto != null){
            try {
                ProdutoFacade.remover(idProduto);
                msg = "Produto excluido com sucesso!";
            } catch (SQLException ex) {
                msg = "O produto ja existe em um atendimento";
            }
        }
        
        response.sendRedirect("ProdutoServlet?msg=" + msg);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Produto produto = new Produto();
        produto = getProdutoParameters(request);
        ProdutoFacade.alterar(produto);
        
        response.sendRedirect("ProdutoServlet");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Produto produto = new Produto();
        produto = getProdutoParameters(request);
        
        ProdutoFacade.inserir(produto);
        
        response.sendRedirect("ProdutoServlet");
    }
    
    private Produto getProdutoParameters(HttpServletRequest request){
        Produto produto = new Produto();
        if (request.getParameter("id") != null){
            produto.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()){
            out.println("O NOME EHHHH " + request.getParameter("nome"));
            produto.setNome(request.getParameter("nome"));
        }
        
        if (request.getParameter("peso") != null && !request.getParameter("peso").isEmpty()){
            produto.setPeso(Integer.parseInt(request.getParameter("peso")));
        }
        
        if (request.getParameter("categoriaId") != null && request.getParameter("categoriaId") != null){
            produto.setCategoriaId(Integer.parseInt(request.getParameter("categoriaId")));
        }
        
        if (request.getParameter("description") != null && !request.getParameter("description").isEmpty()){
            produto.setDescricao(request.getParameter("description"));
        }
        
        return produto;
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
