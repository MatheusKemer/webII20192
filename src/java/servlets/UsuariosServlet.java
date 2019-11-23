/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Usuario;
import beans.ValidaCPF;
import beans.LoginBean;
import facade.CidadesFacade;
import facade.UsuariosFacade;
import facade.EstadoFacade;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/UsuariosServlet"})
public class UsuariosServlet extends HttpServlet {

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
                case "show":
                    show(request, response);
                    return;
                case "formUpdate":
                    edit("1", request, response);
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

        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void newObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
        request.setAttribute("action", "UsuariosServlet?action=new");
        request.setAttribute("estados", EstadoFacade.listar());
        request.setAttribute("titleLabel", "Novo Cliente");
        rd.forward(request, response);
    }
    
    private void edit(String usuarioId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = null;
                    
        usuario = UsuariosFacade.buscar(usuarioId);

        if (usuario != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/alterar-dados.jsp");
            request.setAttribute("usuario", usuario);
            request.setAttribute("editing", true);
            request.setAttribute("action", "UsuariosServlet?action=update");
            request.setAttribute("estados", EstadoFacade.listar());
            request.setAttribute("cidades", CidadesFacade.listarTodasDoEstado(String.valueOf(usuario.getCidade().getEstado().getId())));
            request.setAttribute("titleLabel", "Editando Usuário");
            rd.forward(request, response);
        }        
    }
    
    // ESTOU FAZENDO AGORA A PARTE DE DEIXAR O MESMO FORM PARA CRIAR UM USUÁRIO OU EDITA-LO

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            UsuariosFacade.remover(idCliente);
        }
        
        response.sendRedirect("ClientesServlet");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario();
        usuario = getUsuarioParameters(request);
        UsuariosFacade.alterar(usuario);
        
        response.sendRedirect("UsuariosServlet?action=formUpdate");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario = getUsuarioParameters(request);
        
        if (usuario.validPasswordConfirmation()){
            usuario.setSenha(Usuario.converteSenha(usuario.getSenha()));
            UsuariosFacade.inserir(usuario);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuariosServlet?action=formNew");
            request.setAttribute("usuario", usuario);
            request.setAttribute("msg", "Senha e confirmação de senha não conferem!");
            rd.forward(request, response);
        }
        
        response.sendRedirect("usuarios.jsp");
    }
    
    private Usuario getUsuarioParameters(HttpServletRequest request){
        Usuario usuario = new Usuario();
        if (request.getParameter("id") != null){
            usuario.setId(Integer.parseInt(request.getParameter("id")));
        }
        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setCep(request.getParameter("cep"));
        usuario.setRua(request.getParameter("rua"));
        usuario.setCidadeId(Integer.parseInt(request.getParameter("cidadeId")));
        usuario.setNumero(request.getParameter("numero"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setEstadoId(Integer.parseInt(request.getParameter("estadoId")));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setConfirmacaoSenha(request.getParameter("confirmacao_senha"));
        
        return usuario;
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
