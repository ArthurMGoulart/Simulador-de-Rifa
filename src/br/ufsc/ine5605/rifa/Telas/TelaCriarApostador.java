/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlApostador;
import br.ufsc.ine5605.rifa.Controles.CtrlPrincipal;
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
public class TelaCriarApostador extends Tela{
    
    private static TelaCriarApostador instancia;
    
    private JLabel criandoApostador;
    
    private JLabel nomeDoApostador;
    
    private JLabel cpfDoApostador;
    
    private JTextField inserirNome;
    
    private JTextField inserirCpf;
    
    private JButton criarApostador;
    
    private JButton voltar;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaCriarApostador(){
    
        super("Criando Apostador");
        
        iniciarComponentes();
    
    }
    
    public static TelaCriarApostador getInstancia(){
        
        if(instancia == null){
        
            instancia = new TelaCriarApostador();
        
        }
        
        return instancia;  
    
    }
    
    private void iniciarComponentes(){
    
        criandoApostador = new JLabel("Criando Apostador");
        
        nomeDoApostador = new JLabel("Insira o Nome do Apostador: ");
        
        cpfDoApostador = new JLabel("Insira o CPF do Apostador: ");
        
        inserirNome = new JTextField(10);
        
        inserirCpf = new JTextField(10);
        
        criarApostador = new JButton("Criar Apostador!");
        
        voltar = new JButton("Voltar");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,70,0);
        
        container.add(criandoApostador, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        container.add(nomeDoApostador, c);
        
        c.gridx = 1;
        
        container.add(inserirNome, c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        container.add(cpfDoApostador, c);
        
        c.gridx = 1;
        
        container.add(inserirCpf, c);
        
        c.gridx = 0;
        
        c.gridy = 3;
        
        container.add(criarApostador, c);
        
        c.gridy = 4;
        
        container.add(voltar, c);
        
        inserirNome.addActionListener(gerenciador);
        
        inserirCpf.addActionListener(gerenciador);
        
        criarApostador.addActionListener(gerenciador);
        
        voltar.addActionListener(gerenciador);
        
    }

    public void ligar() {
        
        setVisible(true);
        
    }
    
    public void desligar(){
    
        setVisible(false);
    
    }
    
    public void iniciarCriarApostador(){
        
        String nomeTeste = inserirNome.getText();
        
        String cpfTeste = inserirCpf.getText();
        
        try{
            
            String nome = receberTexto(nomeTeste);
            
            int cpf = receberValorInteiro(cpfTeste);

            CtrlApostador.getInstancia().criarApostador(nome, cpf);
            
            JOptionPane.showMessageDialog(null, "Apostador Criado Com Sucesso!");
            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            CtrlApostador.getInstancia().realizaAcaoCriarApostador(ae.getActionCommand());
                    
        }

    }
    
}
