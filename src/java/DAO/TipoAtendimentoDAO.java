/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Produto;
import beans.TipoAtendimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class TipoAtendimentoDAO {
    public List<TipoAtendimento> buscarTodos(){
        List<TipoAtendimento> resultados = new ArrayList<TipoAtendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_tipo_atendimento, nome_tipo_atendimento FROM tb_tipo_atendimento");
            rs = st.executeQuery();
            while (rs.next()) {
                TipoAtendimento tipoAtendimento = new TipoAtendimento();
                tipoAtendimento.setId( rs.getInt("id_tipo_atendimento") );
                tipoAtendimento.setNome( rs.getString("nome_tipo_atendimento") );
                resultados.add(tipoAtendimento);
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
