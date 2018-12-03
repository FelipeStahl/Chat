/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat;

import br.com.entidade.Aba;
import br.com.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class ManterUsuario {
    private static Usuario usuario;
    private static List<Aba> abas;

    public static List<Aba> getAbas() {
        if(abas == null){
            abas = new ArrayList();
        }
        return abas;
    }

    public static Usuario getUsuario() {
        return usuario;
    }
    
    public static void add(Aba aba){
        if(abas == null){
            abas = new ArrayList();
        }
        abas.add(aba);
    }
    public static void setUsuario(Usuario usuario) {
        ManterUsuario.usuario = usuario;
    }
}
