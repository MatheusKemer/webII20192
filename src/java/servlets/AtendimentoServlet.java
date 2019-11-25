/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Atendimento;
import beans.LoginBean;
import beans.Usuario;
import facade.AtendimentoFacade;
import facade.ProdutoFacade;
import facade.TipoAtendimentoFacade;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Date;
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
        
        Usuario usuario = (Usuario)session.getAttribute("usuario"); 

        if (usuario == null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            rd.forward(request, response);
            return;
        }
        
        int idUsuario = (int) usuario.getId();
        String tipoUsuario = usuario.getTipo();
        
        String action = (String)request.getParameter("action");
        if(null != action){
            switch (action) {
                case "list":
                    index(idUsuario, tipoUsuario, request, response);
                    return;
                case "formNew":
                    newObject(request, response);
                    return;
                case "new":
                    create(usuario.getId(), request, response);
                    return;
                case "remove":
                    delete(request, response);
                    return;
                default:
                    index(1, tipoUsuario, request, response);
            }
        } else {
            index(idUsuario, tipoUsuario, request, response);
        }
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
    
    private void index(int usuarioId, String tipoUsuario, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Atendimento> atendimentos = null;
        String tipo = (String)request.getParameter("tipo");
        
        if (!tipoUsuario.equals("Cliente")  ){
            if (tipo == null || tipo.equals("abertos")){
                atendimentos = AtendimentoFacade.buscarAbertos();
            } else if (tipo.equals("todos")) {
                atendimentos = AtendimentoFacade.buscarTodos();
            }
        } else {
            atendimentos = AtendimentoFacade.buscarPorUsuario(usuarioId);
            
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentos.jsp");
        request.setAttribute("listagemAtendimentos", atendimentos);
        rd.forward(request, response);
    }
    
    private void newObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/criar-atendimento.jsp");
        request.setAttribute("produtos", ProdutoFacade.buscarTodos());
        request.setAttribute("tiposAtendimento", TipoAtendimentoFacade.buscarTodos());
        
        rd.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAtendimento = (String) request.getParameter("id");
        if (idAtendimento != null){
            AtendimentoFacade.remover(idAtendimento);
        }
        
        response.sendRedirect("AtendimentoServlet");
    }
    
    private void create(int usuarioId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Atendimento atendimento = new Atendimento();
        atendimento.setDesc(request.getParameter("descricao"));
        atendimento.setProdutoId(Integer.parseInt(request.getParameter("produtoId")));
        atendimento.setTipoAtendimentoId(Integer.parseInt(request.getParameter("tipoAtendimentoId")));
        Date data = new Date();
        atendimento.setData(data);
        atendimento.setStatus("Aberto");
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
