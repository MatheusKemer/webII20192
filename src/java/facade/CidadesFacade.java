/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.CidadeDAO;
import beans.Cidade;
import beans.Estado;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CidadesFacade {
    public static List<Cidade> listarTodas(){
        CidadeDAO dao = new CidadeDAO();
        return dao.buscarTodas();
    }
    
    public static List<Cidade> listarTodasDoEstado(String idEstado){
        CidadeDAO dao = new CidadeDAO();
        return dao.buscaTodasPorEstado(idEstado);
    }
}
