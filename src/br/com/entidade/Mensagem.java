/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidade;

import java.io.Serializable;

/**
 *
 * @author Alunos
 */
public class Mensagem implements Serializable{
    private String msg;
    private Usuario remetente;
    private Usuario destinario;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinario() {
        return destinario;
    }

    public void setDestinario(Usuario destinario) {
        this.destinario = destinario;
    }
    
    
}
