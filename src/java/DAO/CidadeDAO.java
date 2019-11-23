/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CidadeDAO {

    public List<Cidade> buscarTodas() {
        List<Cidade> resultados = new ArrayList<Cidade>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_cidade, nome_cidade, id_estado FROM tb_cidade");
            rs = st.executeQuery();
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId( rs.getInt("id_cidade") );
                cidade.setNome( rs.getString("nome_cidade") );
                cidade.setIdEstado( Integer.parseInt(rs.getString("id_estado")));
                resultados.add(cidade);
            }
            return resultados;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
            if (st!=null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
    
    public List<Cidade> buscaTodasPorEstado(String idEstado) {
        List<Cidade> resultados = new ArrayList<Cidade>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_cidade, nome_cidade, id_estado FROM tb_cidade WHERE id_estado = ?");
            st.setInt(1, Integer.parseInt(idEstado));
            
            rs = st.executeQuery();
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId( rs.getInt("id_cidade") );
                cidade.setNome( rs.getString("nome_cidade") );
                cidade.setIdEstado(Integer.parseInt(rs.getString("id_estado")));
                resultados.add(cidade);
            }
            return resultados;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
            if (st!=null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
}
