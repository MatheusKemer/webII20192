/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.AtendimentoDAO;
import DAO.ClienteDAO;
import beans.Atendimento;
import beans.Cliente;
import java.util.List;

/**
 *
 * @author matheus
 */
public class AtendimentoFacade {
    public static List<Atendimento> buscarPorUsuario(int id){
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarPorUsuario(id);
    }
    public static void inserir(Atendimento atendimento) {
        AtendimentoDAO dao = new AtendimentoDAO();
        dao.inserir(atendimento);
    }

    public static Atendimento buscar(int idAtendimento) {
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscar(idAtendimento);
    }
}
