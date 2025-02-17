/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Atendimento;
import beans.Cliente;
import beans.LoginBean;
import facade.AtendimentoFacade;
import facade.ClientesFacade;
import facade.EstadoFacade;
import facade.ProdutoFacade;
import facade.TipoAtendimentoFacade;
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
@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {

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
                    int id = bean.getId();
                    index(id, request, response);
                    return;
                case "show":
                    show(request, response);
                    return;
                case "formNew":
                    newObject(request, response);
                    return;
                case "new":
                    create(bean.getId(), request, response);
                    return;
                default:
                    index(bean.getId(), request, response);
                    return;
            }
        } else {
            List<Atendimento> atendimentos = AtendimentoFacade.buscarPorUsuario(bean.getId());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
            request.setAttribute("listagemAtendimentos", atendimentos);
            rd.forward(request, response);
            return;
        }
    }
    
    
    private void index(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Atendimento> atendimentos = AtendimentoFacade.buscarPorUsuario(id);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
        request.setAttribute("listagemAtendimentos", atendimentos);
        rd.forward(request, response);
    }
    
    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Atendimento atendimento = null;
                    
        String idAtendimento = (String) request.getParameter("id");
        if (idAtendimento != null){
            atendimento = AtendimentoFacade.buscar(Integer.parseInt(idAtendimento));
        }

        if (atendimento != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoDetalhes.jsp");
            request.setAttribute("atendimento", atendimento);
            rd.forward(request, response);
        }
    }
    
    private void newObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimento.jsp");
        request.setAttribute("produtos", ProdutoFacade.buscarTodos());
        request.setAttribute("tiposAtendimento", TipoAtendimentoFacade.buscarTodos());
        request.setAttribute("clientes", ClientesFacade.buscarTodos());
        
        rd.forward(request, response);
    }

    private void create(int usuarioId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Atendimento atendimento = new Atendimento();
        atendimento.setDesc(request.getParameter("desc"));
        atendimento.setData(Date.valueOf(request.getParameter("data")));
        atendimento.setClienteId(Integer.parseInt(request.getParameter("clienteId")));
        atendimento.setProdutoId(Integer.parseInt(request.getParameter("produtoId")));
        atendimento.setTipoAtendimentoId(Integer.parseInt(request.getParameter("tipoAtendimentoId")));

        if (request.getParameter("status") == null){
            atendimento.setStatus('N');
        } else {
            atendimento.setStatus('S');
        }
        atendimento.setUsuarioId(usuarioId);
        AtendimentoFacade.inserir(atendimento);
        
        response.sendRedirect("AtendimentoServlet?action=index");
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
