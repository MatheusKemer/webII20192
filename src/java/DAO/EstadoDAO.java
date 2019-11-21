/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Usuario;
import beans.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class EstadoDAO {
    public List<Estado> buscarTodos(){
        List<Estado> resultados = new ArrayList<Estado>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_estado, nome_estado, sigla_estado FROM tb_estado");
            rs = st.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId( rs.getInt("id_estado") );
                estado.setNome( rs.getString("nome_estado") );
                estado.setUf( rs.getString("sigla_estado") );
                resultados.add(estado);
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
