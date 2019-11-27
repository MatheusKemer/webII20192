/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Cidade;
import beans.Estado;
import beans.Usuario;
import static java.lang.System.out;
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
public class UsuarioDAO {
    public Usuario checkLogin(Usuario user){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String response = "";
        Usuario usuario = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("select id_usuario, nome_usuario, tipo_usuario from tb_usuario where email_usuario = ? and senha_usuario = ?");
            st.setString(1, user.getEmail());
            st.setString(2, user.getSenha());
            
            rs = st.executeQuery();
            while (rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setTipo(rs.getString("tipo_usuario"));
            }
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
        
        return usuario;
    }
        
    public List<Usuario> buscarTodos(){
        List<Usuario> resultados = new ArrayList<Usuario>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_usuario, cpf_usuario, nome_usuario, email_usuario FROM tb_usuario");
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId( rs.getInt("id_cliente") );
                usuario.setNome( rs.getString("nome_cliente") );
                usuario.setCpf( rs.getString("cpf_cliente") );
                usuario.setEmail( rs.getString("email_cliente") );
                resultados.add(usuario);
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
    
    public List<Usuario> buscarAdmins(){
        List<Usuario> resultados = new ArrayList<Usuario>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_usuario, cpf_usuario, nome_usuario, email_usuario, data_usuario, telefone_usuario, tb_usuario.id_usuario, tb_usuario.id_cidade, rua_usuario, nr_usuario, cep_usuario, tb_cidade.nome_cidade, tb_cidade.id_estado, nome_estado, sigla_estado, tipo_usuario FROM tb_usuario, tb_cidade, tb_estado WHERE tb_usuario.id_cidade = tb_cidade.id_cidade AND tb_cidade.id_estado = tb_estado.id_estado AND tipo_usuario in ('Gerente', 'Funcionario')");
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId( rs.getInt("id_usuario") );
                usuario.setNome( rs.getString("nome_usuario") );
                usuario.setCpf( rs.getString("cpf_usuario") );
                usuario.setEmail( rs.getString("email_usuario") );
                usuario.setRua( rs.getString("rua_usuario") );
                usuario.setCidadeId( rs.getInt("id_cidade") );
                usuario.setCep( rs.getString("cep_usuario") );
                usuario.setNumero( rs.getString("nr_usuario") );
                usuario.setTelefone( rs.getString("telefone_usuario") );
                usuario.setTipo( rs.getString("tipo_usuario") );
                
                Cidade cidade = new Cidade();
                cidade.setIdEstado( rs.getInt("id_estado"));
                cidade.setId( Integer.parseInt(rs.getString("id_cidade")));
                cidade.setNome( rs.getString("nome_cidade"));
                
                Estado estado = new Estado();
                estado.setId( rs.getInt("id_estado"));
                estado.setNome( rs.getString("nome_estado"));
                estado.setUf( rs.getString("sigla_estado"));
                
                cidade.setEstado(estado);
                usuario.setCidade(cidade);
                resultados.add(usuario);
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
    
    public void remover(int idCliente){
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("delete from tb_usuario where id_usuario = ?");
            st.setInt(1, idCliente);
        
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

    public Usuario buscaUsuario(int idUsuario) {
        Usuario usuario = new Usuario();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_usuario, cpf_usuario, nome_usuario, email_usuario, data_usuario, telefone_usuario, tb_usuario.id_usuario, tb_usuario.id_cidade, rua_usuario, nr_usuario, cep_usuario, tb_cidade.nome_cidade, tb_cidade.id_estado, nome_estado, sigla_estado, tipo_usuario FROM tb_usuario, tb_cidade, tb_estado WHERE id_usuario = ? AND tb_usuario.id_cidade = tb_cidade.id_cidade AND tb_cidade.id_estado = tb_estado.id_estado");
            st.setInt(1, idUsuario);
            
            rs = st.executeQuery();
            while (rs.next()) {
                usuario.setId( rs.getInt("id_usuario") );
                usuario.setNome( rs.getString("nome_usuario") );
                usuario.setCpf( rs.getString("cpf_usuario") );
                usuario.setEmail( rs.getString("email_usuario") );
                usuario.setRua( rs.getString("rua_usuario") );
                usuario.setCidadeId( rs.getInt("id_cidade") );
                usuario.setCep( rs.getString("cep_usuario") );
                usuario.setNumero( rs.getString("nr_usuario") );
                usuario.setTelefone( rs.getString("telefone_usuario") );
                usuario.setTipo( rs.getString("tipo_usuario") );
                
                cidade.setIdEstado( rs.getInt("id_estado"));
                cidade.setId( Integer.parseInt(rs.getString("id_cidade")));
                cidade.setNome( rs.getString("nome_cidade"));
                
                estado.setId( rs.getInt("id_estado"));
                estado.setNome( rs.getString("nome_estado"));
                estado.setUf( rs.getString("sigla_estado"));
                
                cidade.setEstado(estado);
                usuario.setCidade(cidade);
            }
            
            return usuario;
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
    
    public Usuario buscaPorEmail(String email) {
        Usuario usuario = null;
        Cidade cidade = new Cidade();
        Estado estado = new Estado();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT id_usuario, senha_usuario, tipo_usuario, cpf_usuario, nome_usuario, email_usuario, data_usuario, tb_usuario.id_usuario, tb_usuario.id_cidade, rua_usuario, nr_usuario, cep_usuario, tb_cidade.nome_cidade, tb_cidade.id_estado, nome_estado, sigla_estado FROM tb_usuario, tb_cidade, tb_estado WHERE email_usuario = ? AND tb_usuario.id_cidade = tb_cidade.id_cidade AND tb_cidade.id_estado = tb_estado.id_estado");
            st.setString(1, email);
            
            rs = st.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId( rs.getInt("id_usuario") );
                usuario.setNome( rs.getString("nome_usuario") );
                usuario.setCpf( rs.getString("cpf_usuario") );
                usuario.setEmail( rs.getString("email_usuario") );
                usuario.setRua( rs.getString("rua_usuario") );
                usuario.setCidadeId( rs.getInt("id_cidade") );
                usuario.setCep( rs.getString("cep_usuario") );
                usuario.setNumero( rs.getString("nr_usuario") );
                usuario.setSenha( rs.getString("senha_usuario") );
                usuario.setTipo(rs.getString("tipo_usuario"));
                
                cidade.setIdEstado( rs.getInt("id_estado"));
                cidade.setId( Integer.parseInt(rs.getString("id_cidade")));
                cidade.setNome( rs.getString("nome_cidade"));
                
                estado.setId( rs.getInt("id_estado"));
                estado.setNome( rs.getString("nome_estado"));
                estado.setUf( rs.getString("sigla_estado"));
                
                cidade.setEstado(estado);
                usuario.setCidade(cidade);
            }
            
            return usuario;
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
    
    public void atualizar(Usuario usuario){
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("update tb_usuario set nome_usuario = ?, email_usuario = ?, data_usuario = ?, rua_usuario = ?, nr_usuario = ?, cep_usuario = ?, id_cidade = ?, telefone_usuario = ?, tipo_usuario = ? where id_usuario = ?");
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setDate(3, (Date) usuario.getData());
            st.setString(4, usuario.getRua());
            st.setInt(5, Integer.valueOf(usuario.getNumero()));
            st.setString(6, usuario.getCep());
            st.setInt(7, usuario.getCidadeId());
            st.setString(8, usuario.getTelefone());
            st.setString(9, usuario.getTipo());
            st.setInt(10, usuario.getId());
            
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
    
    public void inserir(Usuario usuario) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("INSERT INTO tb_usuario (cpf_usuario, nome_usuario, email_usuario, rua_usuario, telefone_usuario, nr_usuario, cep_usuario, id_cidade, senha_usuario, bairro_usuario, complemente_usuario, tipo_usuario, data_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, usuario.getCpf());
            st.setString(2, usuario.getNome());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getRua());
            st.setString(5, usuario.getTelefone());
            st.setInt(6, Integer.valueOf(usuario.getNumero()));
            st.setString(7, usuario.getCep());
            st.setInt(8, usuario.getCidadeId());
            st.setString(9, usuario.getSenha());
            st.setString(10, usuario.getBairro());
            st.setString(11, usuario.getComplemento());
            st.setString(12, usuario.getTipo());
            st.setDate(13, (Date) usuario.getData());
            
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
