/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

/**
 *
 * @author Alunos
 */
public class Start {
    public static void main(String[] args) {
        Server serv = new Server();
        Thread t = new Thread(serv);
        t.start();
    }
}
