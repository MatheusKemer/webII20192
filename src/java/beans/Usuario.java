/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.UsuarioDAO;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class Usuario implements Serializable{
    public int id;
    public String cpf;
    public String nome;
    public String email;
    public Date data;
    public String rua;
    public String numero;
    public String cep;
    public int cidadeId;
    public int estadoId;
    public String uf;
    public Cidade cidade;
    private String senha;
    private String confirmacao_senha;
    private String tipo;
    private String complemento;
    private String bairro;
    private String telefone;
    
    public Usuario(){
    }
    
    public Usuario(String nome, String email, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.nome = nome;
        this.email = email;
        this.senha = converteSenha(senha);
    }

    public Usuario(String email, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.email = email;
        this.senha = converteSenha(senha);
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Date getData() {
        return data;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public int getCidadeId() {
        return cidadeId;
    }
    
    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\D+","");
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep.replaceAll("\\D+","");;
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }    

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getConfirmacaoSenha() {
        return confirmacao_senha;
    }

    public void setConfirmacaoSenha(String confirmacao_senha) {
        this.confirmacao_senha = confirmacao_senha;
    }
    
    public Usuario verifyLogin() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.checkLogin(this);
        return user;
    }
    
    public static String converteSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        
        String encrypted_password = hexString.toString();
        
        return encrypted_password;
    }
    
    public boolean validPasswordConfirmation(){
        return this.getSenha().equals(this.getConfirmacaoSenha());
    }

    public String getSenha() {
        return this.senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setStringData(String date) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        try {
            this.data = (Date)formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
