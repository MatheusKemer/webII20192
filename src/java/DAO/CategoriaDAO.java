/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CategoriaDAO {
    public List<Categoria> buscarTodos(){
        List<Categoria> resultados = new ArrayList<Categoria>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_categoria, nome_categoria FROM tb_categoria");
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId( rs.getInt("id_categoria") );
                categoria.setNome( rs.getString("nome_categoria") );
                
                resultados.add(categoria);
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
    
    public void inserir(Categoria categoria){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("INSERT INTO tb_categoria (nome_categoria) VALUES (?)");
            st.setString(1, categoria.getNome());
            
            st.executeUpdate();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (st!=null)
                try {st.close();} catch (SQLException e){}
            if (con!=null)
                try {con.close();} catch (SQLException e){}
        }
    }
    
    public void atualizar(Categoria categoria){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("UPDATE tb_categoria SET nome_categoria = ? where id_categoria = ?");
            st.setString(1, categoria.getNome());
            st.setInt(2, categoria.getId());
            
            st.executeUpdate();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (st!=null)
                try {st.close();} catch (SQLException e){}
            if (con!=null)
                try {con.close();} catch (SQLException e){}
        }
    }
    
    public void remover(int idCategoria) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("delete from tb_categoria where id_categoria = ?");
            st.setInt(1, idCategoria);
        
            st.executeUpdate();
        }
        finally {
            if (st!=null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
}
