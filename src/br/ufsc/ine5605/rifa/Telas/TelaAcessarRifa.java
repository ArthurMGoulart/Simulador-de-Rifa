/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlRifa;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author 05380317006
 */
public class TelaAcessarRifa extends Tela implements Serializable{
    
    private static TelaAcessarRifa instancia;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private JLabel tituloGeral;
    
    private JLabel codigoRifa;
    
    private JTextField inserirCodigo;
    
    private JButton botaoFinalizar;
    
    private JButton voltar;
    
    private Container container;

    private TelaAcessarRifa(){
        
        super("Tela Acessar Rifa");
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes() {
        
        tituloGeral = new JLabel("Acessando Rifa");
        
        codigoRifa = new JLabel("Insira o codigo da rifa: ");
        
        inserirCodigo = new JTextField(10);
        
        botaoFinalizar = new JButton("Acessar Rifa");
        
        voltar = new JButton("Voltar");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,70,0);
        
        container.add(tituloGeral, c);
        
        c.insets = new Insets(0,0,30,0);
        
        c.gridy = 1;
        
        container.add(codigoRifa, c);
        
        c.gridx = 1;
        
        container.add(inserirCodigo, c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        container.add(botaoFinalizar, c);
        
        c.gridy = 3;
        
        container.add(voltar, c);
        
        inserirCodigo.addActionListener(gerenciador);
        
        botaoFinalizar.addActionListener(gerenciador);
        
        voltar.addActionListener(gerenciador);
             
    }
    
    public static TelaAcessarRifa getInstancia(){
        
        if(instancia == null){
            
            instancia = new TelaAcessarRifa();
        
        }
        
        return instancia;   
    
    }

    public void ligar() {
        
        setVisible(true);
        
    }
    
    public void desligar(){
    
        setVisible(false);
        
    }

    public void iniciarAcessarRifa() {
        
        String codigoTeste = inserirCodigo.getText();
        
        try{
            
            int codigo = receberValorInteiro(codigoTeste);
            
            CtrlRifa.getInstancia().acessarRifa(codigo);
            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            CtrlRifa.getInstancia().realizarAcaoAcessarRifa(ae.getActionCommand());
            
        }

    }
    
}