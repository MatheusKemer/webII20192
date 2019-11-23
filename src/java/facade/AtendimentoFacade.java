/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.AtendimentoDAO;
import beans.Atendimento;
import java.util.List;

/**
 *
 * @author matheus
 */
public class AtendimentoFacade {
    public static List<Atendimento> buscarTodos(){
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarTodos();
    }
    public static List<Atendimento> buscarPorUsuario(int id){
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarPorUsuario(id);
    }
    public static void inserir(Atendimento atendimento) {
        AtendimentoDAO dao = new AtendimentoDAO();
        dao.inserir(atendimento);
    }
    public static List<Atendimento> buscarAbertos() {
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarPorStatus("Aberto");
    }
    public static List<Atendimento> buscaPorTipo(String tipo) {
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarPorTipo(tipo);
    }
    public static List<Atendimento> buscaAbertoPorTipo(String tipo) {
        AtendimentoDAO dao = new AtendimentoDAO();
        return dao.buscarAbertoPorTipo(tipo);
    }
}
