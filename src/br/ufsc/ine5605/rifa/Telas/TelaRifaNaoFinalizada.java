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

/**
 *
 * @author budi
 */
public class TelaRifaNaoFinalizada extends Tela implements Serializable{
    
    private static TelaRifaNaoFinalizada instancia;
    
    private JLabel codigoDaRifa;
    
    private Botao adicionarProduto;
    
    private Botao listarProdutos;
    
    private Botao listarApostadores;
    
    private Botao finalizarRifa;
    
    private Botao voltar;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaRifaNaoFinalizada(){
        
        super("Rifa");
        
        iniciarComponentes();
        
    }
    
    public static TelaRifaNaoFinalizada getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaRifaNaoFinalizada();
        
        }
        
        return instancia;
    
    }
    
    public void iniciarComponentes(){
        
        if(CtrlRifa.getInstancia().getRifaControlada() != null){
        
            codigoDaRifa = new JLabel("Rifa " + CtrlRifa.getInstancia().getRifaControlada().getCodigo());  
            
        }    
        
        adicionarProduto = new Botao("Adicionar Produto", AcoesBotao.AdicionarProdutoRifaNaoFinalizada);

        listarProdutos = new Botao("Listar Produtos", AcoesBotao.ListarProdutosRifaNaoFinalizada);

        listarApostadores = new Botao("Listar Apostadores Associados", AcoesBotao.ListarApostadoresRifaNaoFinalizada);

        finalizarRifa = new Botao("Finalizar Rifa", AcoesBotao.FinalizarRifaNaoFinalizada);
        
        voltar = new Botao("Voltar", AcoesBotao.VoltarRifaNaoFinalizada);

        container = getContentPane();

        container.setLayout(new GridBagLayout());

        setSize(700,320);

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,0,50,0);

        container.add(codigoDaRifa, c);

        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 2;

        container.add(adicionarProduto, c);

        c.gridy = 3;

        container.add(listarProdutos, c);

        c.gridy = 4;

        container.add(listarApostadores, c);

        c.gridy = 5;

        container.add(finalizarRifa, c);
        
        c.gridy = 6;
        
        container.add(voltar, c);

        adicionarProduto.addActionListener(gerenciador);

        listarProdutos.addActionListener(gerenciador);

        listarApostadores.addActionListener(gerenciador);
        
        finalizarRifa.addActionListener(gerenciador);

        voltar.addActionListener(gerenciador);

    }

    public void ligar() {
        
        setVisible(true);
        
    }
    
    public void desligar(){
    
        setVisible(false);
        
    }

    public void adicionarProduto(String nome, int preco) {
     
        CtrlRifa.getInstancia().adicionarProduto(nome, preco);
       
    }

    public void atualizarCodigoDaRifa() {
        
        GridBagConstraints c = new GridBagConstraints();
        
        container.remove(codigoDaRifa);
        
        codigoDaRifa = new JLabel("Rifa " + CtrlRifa.getInstancia().getRifaControlada().getCodigo());
        
        c.gridy = 0;
        
        c.insets = new Insets(0,0,20,0);
        
        container.add(codigoDaRifa, c);
        
    }

    public void iniciarFinalizarRifa() {
        
        try{
        
            CtrlRifa.getInstancia().finalizarRifa();
            
            JOptionPane.showMessageDialog(null, "Rifa finalizada com Sucesso");
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            Botao teste = (Botao) ae.getSource();
            
            CtrlRifa.getInstancia().realizarAcaoRifaNaoFinalizada(teste.getAcao());
            
        }

    }
    
}