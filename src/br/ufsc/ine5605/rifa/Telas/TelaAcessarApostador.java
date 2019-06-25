/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlApostador;
import br.ufsc.ine5605.rifa.Controles.CtrlRifa;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author budi
 */
public class TelaAcessarApostador extends Tela{
    
    private static TelaAcessarApostador instancia;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private JLabel tituloGeral;
    
    private JLabel cpfApostador;
    
    private JTextField inserirCpf;
    
    private JButton botaoFinalizar;
    
    private JButton voltar;
    
    private Container container;
    
    
    private TelaAcessarApostador(){
    
        super("Acessando Apostador");
        
        iniciarComponentes();
    
    }
    
    public static TelaAcessarApostador getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaAcessarApostador();
        
        }
        
        return instancia;
    
    }

    private void iniciarComponentes() {
        
        tituloGeral = new JLabel("Acessando Apostador");
        
        cpfApostador = new JLabel("Insira o CPF do Apostador: ");
        
        inserirCpf = new JTextField(10);
        
        botaoFinalizar = new JButton("Acessar Apostador");
        
        voltar = new JButton("Voltar");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,70,0);
        
        container.add(tituloGeral, c);
        
        c.insets = new Insets(0,0,30,0);
        
        c.gridy = 1;
        
        container.add(cpfApostador, c);
        
        c.gridx = 1;
        
        container.add(inserirCpf, c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        container.add(botaoFinalizar, c);
        
        c.gridy = 3;
        
        container.add(voltar, c);
        
        botaoFinalizar.addActionListener(gerenciador);
        
        inserirCpf.addActionListener(gerenciador);
        
        voltar.addActionListener(gerenciador);
                   
    }
    
    public void ligar(){
    
        setVisible(true);
    
    }
    
    public void desligar(){
        
        setVisible(false);
    
    }

    public void iniciarAcessarApostador() {
        
        String cpfTeste = inserirCpf.getText();
        
        try{
            
            int cpf = receberValorInteiro(cpfTeste);
            
            CtrlApostador.getInstancia().acessarApostador(cpf);
            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
        
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            CtrlApostador.getInstancia().realizarAcaoAcessarApostador(ae.getActionCommand());
            
        }

    }
    
}
