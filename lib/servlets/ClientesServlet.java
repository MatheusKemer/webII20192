/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.ClienteDAO;
import DAO.UsuarioDAO;
import Model.Usuario;
import Model.ValidaCPF;
import beans.Cliente;
import beans.LoginBean;
import facade.CidadesFacade;
import facade.ClientesFacade;
import facade.EstadoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
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
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

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

        if (bean == null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            rd.forward(request, response);
            return;
        }
        
        String action = (String)request.getParameter("action");
        if(null != action){
            switch (action) {
                case "list":
                    index(request, response);
                    return;
                case "show":
                    show(request, response);
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
                    return;
            }
        } else {
            List<Cliente> clientesCadastrados = ClientesFacade.buscarTodos();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
            request.setAttribute("listagemClientes", clientesCadastrados);
            rd.forward(request, response);
            return;
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

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientesCadastrados = ClientesFacade.buscarTodos();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
        request.setAttribute("listagemClientes", clientesCadastrados);
        rd.forward(request, response);
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = null;
                    
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            cliente = ClientesFacade.buscar(idCliente);
        }

        if (cliente != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clienteVisualizar.jsp");
            request.setAttribute("cliente", cliente);
            rd.forward(request, response);
        }
    }

    private void newObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
        request.setAttribute("action", "ClientesServlet?action=new");
        request.setAttribute("editing", false);
        request.setAttribute("estados", EstadoFacade.listar());
        request.setAttribute("titleLabel", "Novo Cliente");
        rd.forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = null;
                    
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            cliente = ClientesFacade.buscar(idCliente);
        }

        if (cliente != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
            request.setAttribute("cliente", cliente);
            request.setAttribute("editing", true);
            request.setAttribute("action", "ClientesServlet?action=update");
            request.setAttribute("estados", EstadoFacade.listar());
            request.setAttribute("cidades", CidadesFacade.listarTodasDoEstado(String.valueOf(cliente.getCidade().getEstado().getId())));
            request.setAttribute("titleLabel", "Editando Cliente");
            rd.forward(request, response);
        }        
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            ClientesFacade.remover(idCliente);
        }
        
        response.sendRedirect("ClientesServlet");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cliente cliente = new Cliente();
        cliente = getClienteParameters(request);
        ClientesFacade.alterar(cliente);
        
        response.sendRedirect("ClientesServlet");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cliente cliente = new Cliente();
        cliente = getClienteParameters(request);
        
        ClientesFacade.inserir(cliente);
        
        response.sendRedirect("ClientesServlet");
    }
    
    private Cliente getClienteParameters(HttpServletRequest request){
        Cliente cliente = new Cliente();
        if (request.getParameter("id") != null){
            cliente.setId(Integer.parseInt(request.getParameter("id")));
        }
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setData(Date.valueOf(request.getParameter("data")));
        cliente.setCep(request.getParameter("cep"));
        cliente.setRua(request.getParameter("rua"));
        cliente.setCidadeId(Integer.parseInt(request.getParameter("cidadeId")));
        cliente.setNumero(request.getParameter("numero"));
        cliente.setEstadoId(Integer.parseInt(request.getParameter("estadoId")));
        
        return cliente;
    }

}
