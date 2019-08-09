/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlPrincipal;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author budi
 */
public class TelaPrincipal extends Tela implements Serializable{
    
    private static CtrlPrincipal controlador = CtrlPrincipal.getInstancia();
    
    private Botao botaoCriarRifa;
    
    private Botao botaoAcessarRifa;
    
    private Botao botaoCriarApostador;
    
    private Botao botaoAcessarApostador;
    
    private Botao botaoFecharPrograma;
    
    private Container menu;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private static TelaPrincipal instancia;
            
    private TelaPrincipal(){
    
        super("MENU");
        
        iniciarComponentes();
        
    }
    
    public void iniciarComponentes(){
    
        this.botaoCriarRifa = new Botao("Criar Rifa", AcoesBotao.CriarRifaMenu);
        
        this.botaoAcessarRifa = new Botao("Acessar Rifa", AcoesBotao.AcessarRifaMenu);
        
        this.botaoCriarApostador = new Botao("Criar Apostador", AcoesBotao.CriarApostadorMenu);
        
        this.botaoAcessarApostador = new Botao("Acessar Apostador", AcoesBotao.AcessarApostadorMenu); 
        
        this.botaoFecharPrograma = new Botao("Encerrar Programa", AcoesBotao.Encerrar);
        
        botaoCriarRifa.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 30));
        
        botaoCriarApostador.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 30));
        
        botaoAcessarRifa.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 30));
        
        botaoAcessarApostador.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 30));
        
        botaoFecharPrograma.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 30));
        
        botaoFecharPrograma.setSize(300, 300);
          
        menu = getContentPane();
         
        menu.setLayout(new GridBagLayout());
        
        setSize(720, 300);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(5, 5, 10,10);
        
        menu.add(botaoCriarRifa, c);
        
        c.gridx = 1;
        
        menu.add(botaoAcessarRifa, c);
        
        c.gridx = 0;
        
        c.gridy = 1;
        
        menu.add(botaoCriarApostador, c);
        
        c.gridx = 1;
        
        c.gridy = 1;
        
        menu.add(botaoAcessarApostador, c);
        
        c.gridx = 0;
        
        c.anchor = GridBagConstraints.LINE_START;
        
        c.gridy = 2;
        
        c.gridwidth = 2;
        
        menu.add(botaoFecharPrograma, c);
 
        botaoCriarRifa.addActionListener(gerenciador);
        
        botaoAcessarRifa.addActionListener(gerenciador);
        
        botaoCriarApostador.addActionListener(gerenciador);
        
        botaoAcessarApostador.addActionListener(gerenciador); 
        
        botaoFecharPrograma.addActionListener(gerenciador);
    
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
            
            Botao teste = (Botao) ae.getSource();
        
            CtrlPrincipal.getInstancia().realizaAcao(teste.getAcao());
    
        }

    }
    
}