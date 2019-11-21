/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author matheus
 */
public class Cidade {
    public int id;
    public String nome;
    public int idEstado;
    public Estado estado;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdEstado() {
        return idEstado;
    }
    
    public Estado getEstado() {
        return this.estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    } 
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
