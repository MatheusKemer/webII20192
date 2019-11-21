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
        LoginBean bean = (LoginBean)session.getAttribute("LoginBean"); 

        //if (bean == null){
        //    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        //    request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
        //    rd.forward(request, response);
        //    return;
        //}
        
        String action = (String)request.getParameter("action");
        if(null != action){
            switch (action) {
                case "list":
                    index(request, response);
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
                    index(request, response);
            }
        } else {
            index(request, response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtosCadastrados = ProdutoFacade.buscarTodos();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/produtos.jsp");
        request.setAttribute("listagemProdutos", produtosCadastrados);
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
        Cliente cliente = null;
                    
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            //cliente = UsuariosFacade.buscar(idCliente);
        }

        if (cliente != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
            request.setAttribute("produto", produto);
            request.setAttribute("editing", true);
            request.setAttribute("action", "ClientesServlet?action=update");
            request.setAttribute("estados", EstadoFacade.listar());
            request.setAttribute("cidades", CidadesFacade.listarTodasDoEstado(String.valueOf(cliente.getCidade().getEstado().getId())));
            request.setAttribute("titleLabel", "Editando Cliente");
            rd.forward(request, response);
        }        
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduto = (String) request.getParameter("id");
        if (idProduto != null){
            ProdutoFacade.remover(idProduto);
        }
        
        response.sendRedirect("ProdutoServlet");
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
        produto.setNome(request.getParameter("nome"));
        produto.setPeso(Integer.parseInt(request.getParameter("peso")));
        produto.setCategoriaId(Integer.parseInt(request.getParameter("categoriaId")));
        produto.setDescription(request.getParameter("description"));
        
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
