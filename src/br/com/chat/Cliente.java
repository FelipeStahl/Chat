/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat;

import br.com.entidade.Mensagem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class Cliente {
    public void enviarMensagem(String msg){
        Socket cliente;
        try {
            cliente = new Socket("localhost", 8787); //192.168.56.1
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            Mensagem mensagem = new Mensagem();
            mensagem.setMsg(msg);
            oos.writeObject(mensagem);
            //PrintStream output = new PrintStream(cliente.getOutputStream());
            //output.println(mensagem);
            cliente.close();
            oos.close();
            //output.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);            
        }               
    }
    
    public void enviarObjeto(Object objeto){
        Socket cliente;
        try {
            cliente = new Socket("localhost", 8787); //192.168.56.1
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(objeto);
            cliente.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }
}
