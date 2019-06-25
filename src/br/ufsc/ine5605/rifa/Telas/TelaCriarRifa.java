/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlPrincipal;
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
 * @author budi
 */
public class TelaCriarRifa extends Tela implements Serializable{
    
    private static TelaCriarRifa instancia;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private JLabel tituloGeral;
    
    private JLabel codigoRifa;
    
    private JLabel lucroRifa;
    
    private JLabel quantidadeNumerosRifa;
    
    private JTextField inserirCodigo;
    
    private JTextField inserirLucro;
    
    private JTextField inserirQuantidadeNumeros;
    
    private JButton finalizarCriar;
    
    private JButton voltar;
    
    private Container container;
    
    private TelaCriarRifa(){
        
        super("Tela Rifa");
        
        iniciarComponentes();
        
    }
    
    public static TelaCriarRifa getInstancia(){
    
        if(instancia == null){
            
            instancia = new TelaCriarRifa();
        
        }
    
        return instancia;
        
    }
    
    public void iniciarComponentes(){
    
        tituloGeral = new JLabel("Criando Rifa");
        
        codigoRifa = new JLabel("Insira o codigo da rifa: ");
        
        lucroRifa = new JLabel("Insira o lucro desejado: ");
        
        quantidadeNumerosRifa = new JLabel("Insira a quantidade de numeros da rifa: ");
        
        inserirCodigo = new JTextField(10);
        
        inserirLucro = new JTextField(10);
        
        inserirQuantidadeNumeros = new JTextField(10);
        
        finalizarCriar = new JButton("Criar Rifa");
        
        voltar = new JButton("Voltar");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());

        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,70,0);
        
        container.add(tituloGeral, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        container.add(codigoRifa,c);
        
        c.gridx = 1;
        
        container.add(inserirCodigo,c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        container.add(lucroRifa,c);
        
        c.gridx = 1;
        
        container.add(inserirLucro, c);
        
        c.gridx = 0;
        
        c.gridy= 3;
        
        container.add(quantidadeNumerosRifa, c);
        
        c.gridx = 1;
        
        container.add(inserirQuantidadeNumeros, c);
        
        c.insets = new Insets(0,0,5,0);

        c.gridx = 0;
        
        c.gridy = 4;
        
        container.add(finalizarCriar, c);
        
        c.gridy = 5;
        
        container.add(voltar, c);
        
        finalizarCriar.addActionListener(gerenciador);
        
        inserirCodigo.addActionListener(gerenciador);
        
        inserirLucro.addActionListener(gerenciador);
        
        inserirQuantidadeNumeros.addActionListener(gerenciador);
        
        voltar.addActionListener(gerenciador);
        
    }

    public void ligar() {
        
        setVisible(true);
 
    }
    
    public void desligar() {
        
        setVisible(false);
 
    }

    public void iniciarCriarRifa(){
        
        String codigoTeste = inserirCodigo.getText();
        
        String lucroTeste = inserirLucro.getText();
        
        String quantidadeNumerosTeste = inserirQuantidadeNumeros.getText();
        
        try{
            
            int codigo = receberValorInteiro(codigoTeste);
            
            int lucro = receberValorInteiro(lucroTeste);
            
            int quantidadeNumeros = receberValorInteiro(quantidadeNumerosTeste);
            
            CtrlRifa.getInstancia().criarRifa(codigo, lucro, quantidadeNumeros);
            
            JOptionPane.showMessageDialog(null, "Rifa Criada Com Sucesso!");
            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            CtrlRifa.getInstancia().realizaAcaoCriarRifa(ae.getActionCommand());
                    
            
        }

    }

}