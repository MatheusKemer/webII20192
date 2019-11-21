/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.ProdutoDAO;
import DAO.TipoAtendimentoDAO;
import beans.Produto;
import beans.TipoAtendimento;
import java.util.List;

/**
 *
 * @author matheus
 */
public class TipoAtendimentoFacade {
    public static List<TipoAtendimento> buscarTodos(){
        TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
        return dao.buscarTodos();
    }
}
