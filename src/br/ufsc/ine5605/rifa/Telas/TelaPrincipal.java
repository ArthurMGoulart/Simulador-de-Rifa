/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlPrincipal;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author budi
 */
public class TelaPrincipal extends Tela implements Serializable{
    
    private static CtrlPrincipal controlador = CtrlPrincipal.getInstancia();
    
    private JButton botaoCriarRifa;
    
    private JButton botaoAcessarRifa;
    
    private JButton botaoCriarApostador;
    
    private JButton botaoAcessarApostador;
    
    private Container menu;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private static TelaPrincipal instancia;
            
    private TelaPrincipal(){
    
        super("MENU");
        
        iniciarComponentes();
        
    }
    
    public void iniciarComponentes(){
    
        this.botaoCriarRifa = new JButton("Criar Rifa");
        
        this.botaoAcessarRifa = new JButton("Acessar Rifa");
        
        this.botaoCriarApostador = new JButton("Criar Apostador");
        
        this.botaoAcessarApostador = new JButton("Acessar Apostador"); 
          
        menu = getContentPane();
         
        menu.setLayout(new GridLayout(2,2));
        
        setSize(560,300);
        
        menu.add(botaoCriarRifa);
        
        menu.add(botaoAcessarRifa);
        
        menu.add(botaoCriarApostador);
        
        menu.add(botaoAcessarApostador);
        
        botaoCriarRifa.addActionListener(gerenciador);
        
        botaoAcessarRifa.addActionListener(gerenciador);
        
        botaoCriarApostador.addActionListener(gerenciador);
        
        botaoAcessarApostador.addActionListener(gerenciador); 
    
    }
    
    public void ligar(){   
        
        setVisible(true);
    
    }
    
    public void desligar(){
    
        setVisible(false);
    
    }
    
    public static TelaPrincipal getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaPrincipal();
                
        }
        
        return instancia;
    
    }
    
    public void exibirExecao(String erro){
    
        JOptionPane.showMessageDialog(null, erro);
    
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
        
            CtrlPrincipal.getInstancia().realizaAcao(ae.getActionCommand());
    
        }

    }
    
}