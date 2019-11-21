spot/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Categoria;
import beans.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProdutoDAO {
    public List<Produto> buscarTodos(){
        List<Produto> resultados = new ArrayList<Produto>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_produto, nome_produto, tb_produto.id_categoria, desc_produto, nome_categoria, peso_produto FROM tb_produto, tb_categoria WHERE tb_produto.id_categoria = tb_categoria.id_categoria");
            //st = con.prepareStatement("SELECT id_produto, nome_produto, tb_produto.id_categoria, nome_categoria FROM tb_produto, tb_categoria WHERE tb_produto.id_categoria = tb_categoria.id_categoria");
            rs = st.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId( rs.getInt("id_produto") );
                produto.setNome( rs.getString("nome_produto") );
                produto.setDescricao(rs.getString("desc_produto"))
                produto.setPeso( rs.getInt("peso_produto") );
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome_categoria"));
                
                produto.setCategoria(categoria);
                
                resultados.add(produto);
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
    
    public void inserir(Produto produto){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("INSERT INTO tb_produto (nome_produto, peso_produto, id_categoria) VALUES (?, ?, ?)");
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getPeso());
            st.setInt(3, produto.getCategoriaId());
            
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
    
    public void remover(int idProduto){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("delete from tb_produto where id_produto = ?");
            st.setInt(1, idProduto);
        
            st.executeUpdate();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (st!=null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }

}
