/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class ManterUsuario{
    private static List<Usuario> usuarios;

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public static void add(Usuario usuario){
        if(usuarios == null){
            usuarios = new ArrayList();
        }
        usuarios.add(usuario);
    }
}
