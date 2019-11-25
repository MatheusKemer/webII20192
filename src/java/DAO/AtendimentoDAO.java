/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Atendimento;
import beans.Produto;
import beans.TipoAtendimento;
import beans.Usuario;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author matheus
 */
public class AtendimentoDAO {
    public List<Atendimento> buscarTodos(){
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_atendimento, dt_hr_atendimento, status_atendimento, dsc_atendimento, tb_atendimento.id_tipo_atendimento, nome_usuario, nome_produto, nome_tipo_atendimento, res_atendimento FROM tb_atendimento, tb_produto, tb_usuario, tb_tipo_atendimento WHERE tb_usuario.id_usuario = tb_atendimento.id_usuario AND tb_produto.id_produto = tb_atendimento.id_produto AND tb_atendimento.id_tipo_atendimento = tb_tipo_atendimento.id_tipo_atendimento");
            
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId( rs.getInt("id_atendimento") );
                atendimento.setData( rs.getTimestamp("dt_hr_atendimento") );
                atendimento.setDesc( rs.getString("dsc_atendimento") );
                atendimento.setStatus( rs.getString("status_atendimento"));
                atendimento.setResposta( rs.getString("res_atendimento"));
                
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome_produto"));
                
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                
                atendimento.setUsuario(usuario);
                atendimento.setProduto(produto);
                atendimento.setTipoAtendimento(tipo);
                resultados.add(atendimento);
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
    
    public List<Atendimento> buscarPorUsuario(int id){
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_atendimento, dt_hr_atendimento, status_atendimento, dsc_atendimento, tb_atendimento.id_tipo_atendimento, nome_usuario, nome_produto, nome_tipo_atendimento FROM tb_atendimento, tb_produto, tb_usuario, tb_tipo_atendimento WHERE tb_atendimento.id_usuario = ? AND tb_usuario.id_usuario = tb_atendimento.id_usuario AND tb_produto.id_produto = tb_atendimento.id_produto AND tb_atendimento.id_tipo_atendimento = tb_tipo_atendimento.id_tipo_atendimento");
            st.setInt(1, id);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId( rs.getInt("id_atendimento") );
                atendimento.setData( rs.getTimestamp("dt_hr_atendimento") );
                atendimento.setDesc( rs.getString("dsc_atendimento") );
                atendimento.setStatus( rs.getString("status_atendimento"));
                
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome_produto"));
                
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                
                atendimento.setUsuario(usuario);
                atendimento.setProduto(produto);
                atendimento.setTipoAtendimento(tipo);
                resultados.add(atendimento);
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
    
    public void inserir(Atendimento atendimento){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("INSERT INTO tb_atendimento (dt_hr_atendimento, dsc_atendimento, id_produto, id_usuario, id_tipo_atendimento, status_atendimento) VALUES (?, ?, ?, ?, ?, ?)");
            st.setDate(1, new java.sql.Date((atendimento.getData().getTime())));
            st.setString(2, atendimento.getDesc());
            st.setInt(3, atendimento.getProdutoId());
            st.setInt(4, atendimento.getUsuarioId());
            st.setInt(5, atendimento.getTipoAtendimentoId());
            st.setString(6, String.valueOf(atendimento.getStatus()));
            
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
    
    public List<Atendimento> buscarPorStatus(String status){
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_atendimento, dt_hr_atendimento, status_atendimento, dsc_atendimento, tb_atendimento.id_tipo_atendimento, nome_usuario, nome_produto, nome_tipo_atendimento FROM tb_atendimento, tb_produto, tb_usuario, tb_tipo_atendimento WHERE tb_atendimento.status_atendimento = ? AND tb_usuario.id_usuario = tb_atendimento.id_usuario AND tb_produto.id_produto = tb_atendimento.id_produto AND tb_atendimento.id_tipo_atendimento = tb_tipo_atendimento.id_tipo_atendimento ORDER BY tb_atendimento.dt_hr_atendimento");
            st.setString(1, status);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId( rs.getInt("id_atendimento") );
                atendimento.setData( rs.getTimestamp("dt_hr_atendimento") );
                atendimento.setDesc( rs.getString("dsc_atendimento") );
                atendimento.setStatus( rs.getString("status_atendimento"));
                
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome_produto"));
                
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                
                atendimento.setUsuario(usuario);
                atendimento.setProduto(produto);
                atendimento.setTipoAtendimento(tipo);
                resultados.add(atendimento);
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
    
    public List<Atendimento> buscarPorTipo(String tipoBusca){
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_atendimento, dt_hr_atendimento, status_atendimento, dsc_atendimento, tb_atendimento.id_tipo_atendimento, nome_usuario, nome_produto, nome_tipo_atendimento FROM tb_atendimento, tb_produto, tb_usuario, tb_tipo_atendimento WHERE tb_tipo_atendimento.nome_tipo_atendimento = ? AND tb_usuario.id_usuario = tb_atendimento.id_usuario AND tb_produto.id_produto = tb_atendimento.id_produto AND tb_atendimento.id_tipo_atendimento = tb_tipo_atendimento.id_tipo_atendimento ORDER BY tb_atendimento.dt_hr_atendimento");
            st.setString(1, tipoBusca);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId( rs.getInt("id_atendimento") );
                atendimento.setData( rs.getTimestamp("dt_hr_atendimento") );
                atendimento.setDesc( rs.getString("dsc_atendimento") );
                atendimento.setStatus( rs.getString("status_atendimento"));
                
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome_produto"));
                
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                
                atendimento.setUsuario(usuario);
                atendimento.setProduto(produto);
                atendimento.setTipoAtendimento(tipo);
                resultados.add(atendimento);
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

    public List<Atendimento> buscarAbertoPorTipo(String tipoBusca){
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_atendimento, dt_hr_atendimento, status_atendimento, dsc_atendimento, tb_atendimento.id_tipo_atendimento, nome_usuario, nome_produto, nome_tipo_atendimento FROM tb_atendimento, tb_produto, tb_usuario, tb_tipo_atendimento WHERE tb_tipo_atendimento.nome_tipo_atendimento = ? AND tb_usuario.id_usuario = tb_atendimento.id_usuario AND tb_produto.id_produto = tb_atendimento.id_produto AND tb_atendimento.id_tipo_atendimento = tb_tipo_atendimento.id_tipo_atendimento AND tb_atendimento.status_atendimento = 'Aberto' ORDER BY tb_atendimento.dt_hr_atendimento");
            st.setString(1, tipoBusca);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId( rs.getInt("id_atendimento") );
                atendimento.setData( rs.getTimestamp("dt_hr_atendimento") );
                atendimento.setDesc( rs.getString("dsc_atendimento") );
                atendimento.setStatus( rs.getString("status_atendimento"));
                
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome_produto"));
                
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                
                atendimento.setUsuario(usuario);
                atendimento.setProduto(produto);
                atendimento.setTipoAtendimento(tipo);
                resultados.add(atendimento);
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
    
    public void remover(int idAtendimento){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("delete from tb_atendimento where id_atendimento = ?");
            st.setInt(1, idAtendimento);
        
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
