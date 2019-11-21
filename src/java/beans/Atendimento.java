/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import org.joda.time.DateTime;

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
    public TipoAtendimento tipoAtendimento;
    public String status;
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

    public String getStatus() {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
}
