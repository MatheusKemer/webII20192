/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.ProdutoDAO;
import beans.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProdutoFacade {
    public static void inserir(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        dao.inserir(produto);
    }
    public static List<Produto> buscarTodos(){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.buscarTodos();
    }
    public static void alterar(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        dao.atualizar(produto);
    }
    public static void remover(String id) throws SQLException{
        ProdutoDAO dao = new ProdutoDAO();
        dao.remover(Integer.parseInt(id));
    }
}
