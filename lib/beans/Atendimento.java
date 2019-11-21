/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Model.Usuario;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author matheus
 */
public class Atendimento implements Serializable {
    public int id;
    public String desc;
    public Date data;
    public Produto produto;
    public Usuario usuario;
    public Cliente cliente;
    public char status;
    public int clienteId;
    public int produtoId;
    public int usuarioId;
    public int tipoAtendimentoId;

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Date getData() {
        return data;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public char getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public void setTipoAtendimentoId(int tipoAtendimentoId) {
        this.tipoAtendimentoId = tipoAtendimentoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public int getTipoAtendimentoId() {
        return tipoAtendimentoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
