/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidade;

import javax.swing.JTextArea;

/**
 *
 * @author Alunos
 */
public class Aba {
    private Usuario destinatario;
    private javax.swing.JTextArea areaTexto;

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public JTextArea getAreaTexto() {
        return areaTexto;
    }

    public void setAreaTexto(JTextArea areaTexto) {
        this.areaTexto = areaTexto;
    }
}
