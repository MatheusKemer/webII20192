/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Model.Cidade;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author matheus
 */
public class Cliente implements Serializable{
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
    
    public Cliente(){
        
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
        this.cpf = cpf;
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
        this.cep = cep;
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
    
}
