/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.CategoriaDAO;
import beans.Categoria;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CategoriaFacade {
    public static void inserir(Categoria categoria){
        CategoriaDAO dao = new CategoriaDAO();
        dao.inserir(categoria);
    }
    public static List<Categoria> buscarTodos(){
        CategoriaDAO dao = new CategoriaDAO();
        return dao.buscarTodos();
    }
    public static void alterar(Categoria categoria){
        CategoriaDAO dao = new CategoriaDAO();
        dao.atualizar(categoria);
    }
    public static void remover(String id){
        CategoriaDAO dao = new CategoriaDAO();
        dao.remover(Integer.parseInt(id));
    }
}
