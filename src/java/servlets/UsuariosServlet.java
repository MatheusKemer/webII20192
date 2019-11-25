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
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.DuplicateKeyException;
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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario"); 

        String action = (String)request.getParameter("action");
        
        if  (usuario == null && !(action != null && ((action.equals("formNew")) || (action.equals("new"))))){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            rd.forward(request, response);
            return;
        }
        
        int idUsuario = 0;
        String tipoUsuario = ""; 
        
        if (usuario != null){
            idUsuario = (int) usuario.getId();
            tipoUsuario = usuario.getTipo();
        }
        
        if(null != action){
            switch (action) {
                case "list":
                    index(request, response);
                    return;
                case "show":
                    show(request, response);
                    return;
                case "formUpdate":
                    if (tipoUsuario.equals("Gerente")){
                        idUsuario = Integer.parseInt((String) request.getParameter("id"));
                    }
                    edit(idUsuario, request, response);
                    return;
                case "remove":
                    delete(request, response);
                    return;
                case "update":
                    update(tipoUsuario, request, response);
                    return;
                case "formNew":
                    newObject(tipoUsuario, request, response);
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuarios.jsp");
        request.setAttribute("listagemUsuarios", UsuariosFacade.buscarAdmins());
        rd.forward(request, response);
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void newObject(String tipo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
        request.setAttribute("action", "UsuariosServlet?action=new");
        request.setAttribute("estados", EstadoFacade.listar());
        if (tipo.equals("Gerente")){
            request.setAttribute("titleLabel", "Novo Usuário");
        } else {
            request.setAttribute("titleLabel", "Novo Cliente");
        }
        rd.forward(request, response);
    }
    
    private void edit(int usuarioId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = null;
                    
        usuario = UsuariosFacade.buscar(usuarioId);
        
        if (usuario != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/alterar-dados.jsp");
            request.setAttribute("cliente", usuario);
            request.setAttribute("editing", true);
            request.setAttribute("action", "UsuariosServlet?action=update");
            request.setAttribute("estados", EstadoFacade.listar());
            request.setAttribute("cidades", CidadesFacade.listarTodasDoEstado(String.valueOf(usuario.getCidade().getEstado().getId())));
            request.setAttribute("titleLabel", "Editando Usuário");
            rd.forward(request, response);
        }        
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCliente = (String) request.getParameter("id");
        if (idCliente != null){
            UsuariosFacade.remover(idCliente);
        }
        
        response.sendRedirect("UsuariosServlet");
    }

    private void update(String tipo, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usuario = new Usuario();
        usuario = getUsuarioParameters(request);
        
        if (usuario.getTipo() == null){
            usuario.setTipo("Cliente");
        }
                
        UsuariosFacade.alterar(usuario);
        
        if (tipo.equals("Gerente")){
            response.sendRedirect("UsuariosServlet");
        } else {
            response.sendRedirect("UsuariosServlet?action=formUpdate");
        }
        
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario = getUsuarioParameters(request);
        
        if (usuario.getTipo() == null){
            usuario.setTipo("Cliente");
        }
        
        if (usuario.validPasswordConfirmation()){
            usuario.setSenha(Usuario.converteSenha(usuario.getSenha()));
            try {
                UsuariosFacade.inserir(usuario);
            } catch (SQLIntegrityConstraintViolationException e){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuariosServlet?action=formNew");
                request.setAttribute("usuario", usuario);
                request.setAttribute("msg", "Email/CPF já está em uso!");
                rd.forward(request, response);
                return;
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuariosServlet?action=formNew");
            request.setAttribute("usuario", usuario);
            request.setAttribute("msg", "Senha e confirmação de senha não conferem!");
            rd.forward(request, response);
        }
        
        response.sendRedirect("UsuariosServlet");
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
        usuario.setStringData(request.getParameter("data"));
        usuario.setCidadeId(Integer.parseInt(request.getParameter("cidadeId")));
        usuario.setNumero(request.getParameter("numero"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setEstadoId(Integer.parseInt(request.getParameter("estadoId")));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setConfirmacaoSenha(request.getParameter("confirmacao_senha"));

        if (request.getParameter("tipo") != null){
            usuario.setTipo(request.getParameter("tipo"));
        }
        
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
