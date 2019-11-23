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
@WebServlet(name = "GerenteServlet", urlPatterns = {"/GerenteServlet"})
public class GerenteServlet extends HttpServlet {

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
        
        if (!tipoUsuario.equals("Gerente")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário não tem permissão para acessar esta páginda!");
            rd.forward(request, response);
            return;
        }
        
        String action = (String)request.getParameter("action");
        if(null != action){
            switch (action) {
                case "list":
                    index(request, response);
                    return;
                default:
                    index(request, response);
            }
        } else {
            index(request, response);
        }
        
        //List<Cliente> clientesCadastrados = UsuariosFacade.buscarTodos();
        //RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
        //request.setAttribute("listagemClientes", clientesCadastrados);
        //rd.forward(request, response);
    }
    
        private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Atendimento> reclamacoes = AtendimentoFacade.buscaPorTipo("Reclamação");
            List<Atendimento> sugestoes = AtendimentoFacade.buscaPorTipo("Sugestão");
            List<Atendimento> criticas = AtendimentoFacade.buscaPorTipo("Crítica");
            List<Atendimento> duvidas = AtendimentoFacade.buscaPorTipo("Problemas");
            List<Atendimento> reclamacoesAbertas = AtendimentoFacade.buscaAbertoPorTipo("Reclamação");
            List<Atendimento> sugestoesAbertas = AtendimentoFacade.buscaAbertoPorTipo("Sugestão");
            List<Atendimento> criticasAbertas = AtendimentoFacade.buscaAbertoPorTipo("Crítica");
            List<Atendimento> duvidasAbertas = AtendimentoFacade.buscaAbertoPorTipo("Problemas");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/gerente.jsp");
            
            request.setAttribute("reclamacoes", reclamacoes.size());
            request.setAttribute("reclamacoesAbertas", reclamacoesAbertas.size());
            request.setAttribute("sugestoes", sugestoes.size());
            request.setAttribute("sugestoesAbertas", sugestoesAbertas.size());
            request.setAttribute("criticas", criticas.size());
            request.setAttribute("criticasAbertas", criticasAbertas.size());
            request.setAttribute("duvidas", duvidas.size());
            request.setAttribute("duvidasAbertas", duvidasAbertas.size());
            request.setAttribute("atendimentosTotais", duvidas.size() + reclamacoes.size() + sugestoes.size() + criticas.size());
            request.setAttribute("atendimentosAbertos", duvidasAbertas.size() + reclamacoesAbertas.size() + sugestoesAbertas.size() + criticasAbertas.size());

            rd.forward(request, response);
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
