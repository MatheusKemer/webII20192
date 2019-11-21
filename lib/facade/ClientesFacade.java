/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.ClienteDAO;
import beans.Cliente;
import static java.lang.System.out;
import java.util.List;

/**
 *
 * @author matheus
 */

public class ClientesFacade {
    public static void inserir(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        dao.inserir(c);
    }
    public static void alterar(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        dao.atualizar(c);
    }
    public static Cliente buscar(String id){
        ClienteDAO dao = new ClienteDAO();
        return dao.buscaCliente(Integer.parseInt(id));
    }
    public static List<Cliente> buscarTodos(){
        ClienteDAO dao = new ClienteDAO();
        return dao.buscarTodos();
    }
    public static void remover(String id){
        ClienteDAO dao = new ClienteDAO();
        dao.remover(Integer.parseInt(id));
    }
}