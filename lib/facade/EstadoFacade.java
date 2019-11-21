/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.EstadoDAO;
import Model.Estado;
import java.util.List;

/**
 *
 * @author matheus
 */
public class EstadoFacade {
    public static List<Estado> listar(){
        EstadoDAO dao = new EstadoDAO();
        return dao.buscarTodos();
    }    
}
