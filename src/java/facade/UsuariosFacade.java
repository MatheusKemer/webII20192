/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.UsuarioDAO;
import beans.Usuario;
import static java.lang.System.out;
import java.util.List;

/**
 *
 * @author matheus
 */

public class UsuariosFacade {
    public static void inserir(Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(usuario);
    }
    public static void alterar(Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizar(usuario);
    }
    public static Usuario buscar(String id){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.buscaUsuario(Integer.parseInt(id));
    }
    public static List<Usuario> buscarTodos(){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.buscarTodos();
    }
    public static void remover(String id){
        UsuarioDAO dao = new UsuarioDAO();
        dao.remover(Integer.parseInt(id));
    }
    public static Usuario buscarPorEmail(String email){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.buscaPorEmail(email);
    }
}