/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.entidade.Mensagem;
import br.com.entidade.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class Server extends Thread {
    private String porta;
    private javax.swing.JTextArea caixa;
    
    public Server(){
        porta = "8787";
    }
    
    public Server(Object caixaTexto, String porta){
        try{
            caixa = (javax.swing.JTextArea)caixaTexto;
            this.porta = porta;
        }catch(Exception e){
            throw e;
        }   
    }
    @Override
    public void run() {
        ServerSocket servidor;
        try {
            servidor = new ServerSocket(8787);
            System.out.println("Servidor iniciado...");
            Socket cliente = null;
            ObjectInputStream ois;
            //Scanner entradaDados;           
            while(true){
                String texto = "";
                cliente = servidor.accept();
                ois = new ObjectInputStream(cliente.getInputStream());
                Object objeto = ois.readObject();
                if(objeto instanceof Mensagem){
                    Mensagem mensagem = (Mensagem)objeto;
                    enviarObjeto(mensagem.getDestinario(), mensagem);
                    System.out.println(mensagem.getMsg() + " | enviado por: " + mensagem.getRemetente().getNome() + " | para: " + mensagem.getDestinario().getNome());
                }else if(objeto instanceof Usuario){
                   Usuario usuario = (Usuario)objeto;
                   ManterUsuario.add(usuario);
                   System.out.println(usuario.getNome() + " Conectado." + usuario.getPorta());
                   List<Usuario> usuarios = ManterUsuario.getUsuarios();
                    for (Usuario usuario1 : usuarios) {
                        enviarObjeto(usuario1, ManterUsuario.getUsuarios());
                    }

                }
                //entradaDados = new Scanner(cliente.getInputStream());
//                String texto = entradaDados.nextLine();
//                caixa.append(cliente.getInetAddress().getHostAddress() + " : " + texto + "\r\n");
                //System.out.println(cliente.getInetAddress().getHostAddress() + " : " + texto);
                if(texto.equals("quit")){
                    break;
                }
            } 
            servidor.close();
            cliente.close();
            ois.close();
            //entradaDados.close();
            System.out.println("Servidor encerrado!");
        }catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void enviarObjeto(Object objeto){
        Socket cliente;
        try {
            cliente = new Socket("localhost", ((Usuario)objeto).getPorta()); //192.168.56.1
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(objeto);
            cliente.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }
        private void enviarObjeto(Usuario destinatario, Object objeto){
        Socket cliente;
        try {
            cliente = new Socket("localhost", destinatario.getPorta()); //192.168.56.1
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(objeto);
            cliente.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }
}
