/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import beans.Cliente;
import beans.Produto;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProdutoFacade {
    public static List<Produto> buscarTodos(){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.buscarTodos();
    }
}
