/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat;

import br.com.entidade.Aba;
import br.com.entidade.Mensagem;
import br.com.entidade.Usuario;
import br.com.form.FrmPrincipal;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Alunos
 */
public class Recebe extends Thread {
    private Integer porta;
    private javax.swing.JList<String> listaView;
    private Boolean conectado;
    private FrmPrincipal frm;
    
    public Integer getPorta() {
        return porta;
    }   

    public Boolean getConectado() {
        return conectado;
    }

    public void setConectado(Boolean conectado) {
        this.conectado = conectado;
    }
    
    public Recebe(Object listaView, FrmPrincipal frm){
        try{
            this.listaView = (javax.swing.JList<String>)listaView;
            this.frm = frm;
        }catch(Exception e){
            throw e;
        }   
    }
    
    @Override
    public void run() {
        ServerSocket servidor = null;
        try {
            porta = 8788;
            Boolean conectado = false;
            while(!conectado){
                try{
                    servidor = new ServerSocket(porta);
                    conectado = true;
                    ManterUsuario.getUsuario().setPorta(porta);
                    Cliente envia = new Cliente();
                    envia.enviarObjeto(ManterUsuario.getUsuario());
                }catch(Exception e){
                    porta += 1;
                }               
            } 
            System.out.println("Servidor iniciado..." + porta);
            Socket cliente = null;
            //Scanner entradaDados;           
            while(true){
                String texto = "";
                cliente = servidor.accept();
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                Object objeto = (Object)ois.readObject();
                
                if(objeto instanceof Mensagem){
                    Mensagem mensagem = (Mensagem)objeto;
                    Boolean certo = false;
                    for (Aba aba : ManterUsuario.getAbas()) {
                        if(mensagem.getRemetente().getPorta().equals(aba.getDestinatario().getPorta())){
                            certo = true;
                            aba.getAreaTexto().append(mensagem.getRemetente().getNome() + " diz:\r\n" + mensagem.getMsg() + "\r\n");
                        }
                    }
                    if(!certo){
                        Aba aba = frm.criarAba(mensagem.getRemetente());
                        aba.getAreaTexto().append(mensagem.getRemetente().getNome() + " diz:\r\n" + mensagem.getMsg() + "\r\n");
                    }
                    System.out.println(mensagem.getMsg());
                }else if(objeto instanceof List){
                   List<Usuario> usuarios = (List<Usuario>)objeto;
                   listaView.removeAll();
                    DefaultListModel aa = new DefaultListModel<Object>();                                       
                    for (Usuario usuario : usuarios) {
                        if(!usuario.getPorta().equals(getPorta())){
                            aa.addElement(usuario);
                        }                        
                    }
                   listaView.setModel(aa);
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
            //entradaDados.close();
            System.out.println("Servidor encerrado!");
        }catch (Exception ex) {
            Logger.getLogger(Recebe.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
