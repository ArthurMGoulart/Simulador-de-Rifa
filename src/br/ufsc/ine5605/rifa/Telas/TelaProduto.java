/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlRifa;
import br.ufsc.ine5605.rifa.Entidades.Produto;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author budi
 */
public class TelaProduto extends Tela implements Serializable{

    private static TelaProduto instancia;
    
    private JLabel nomeProduto;
    
    private JLabel precoProduto;
    
    private JLabel listarProdutos;
    
    private JTextField inserirNome;
    
    private JTextField inserirPreco;
    
    private JButton adicionarProduto;
    
    private JList listaNomesProdutos;
    
    private JPanel painelAdicionar;
    
    private JPanel painelListagem;
    
    private final JButton voltarAdicionar = new JButton("Voltar");
    
    private final JButton voltarListar = new JButton("Voltar");
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaProduto() {
        
        super("Adicionando Produto");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        iniciarComponentesAdicionar();
        
        if(CtrlRifa.getInstancia().getRifaControlada() != null){
        
            iniciarComponentesListar();
        
        }
        
    }
    
    public static TelaProduto getInstancia(){
    
        if(instancia == null){
            
            instancia = new TelaProduto();
        
        }
        
        return instancia;
    
    }
    
    public void iniciarComponentesAdicionar(){
    
        nomeProduto = new JLabel("Insira o nome do Produto: ");
        
        precoProduto = new JLabel("Insira o preco do Produto: ");
        
        inserirNome = new JTextField(10);
        
        inserirPreco = new JTextField(10);
        
        adicionarProduto = new JButton("Adicionar o Produto");
        
        painelAdicionar = new JPanel();
        
        painelAdicionar.setLayout(new GridBagLayout());
        
        painelAdicionar.setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,10,0);
        
        painelAdicionar.add(nomeProduto, c);
        
        c.gridx = 1;
        
        painelAdicionar.add(inserirNome, c);
        
        c.gridx = 0;
        
        c.gridy = 1;
        
        painelAdicionar.add(precoProduto, c);
        
        c.gridx = 1;
        
        painelAdicionar.add(inserirPreco, c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        painelAdicionar.add(adicionarProduto, c);
        
        c.gridy = 3;
        
        painelAdicionar.add(voltarAdicionar, c);
        
        inserirNome.addActionListener(gerenciador);
        
        inserirPreco.addActionListener(gerenciador);
        
        adicionarProduto.addActionListener(gerenciador);
        
        voltarAdicionar.addActionListener(gerenciador);
        
    }
    
    public void iniciarComponentesListar(){
        
        listarProdutos = new JLabel("Listando os Produtos");
        
        painelListagem = new JPanel();
        
        listaNomesProdutos = new JList(CtrlRifa.getInstancia().getNomesProdutoDaRifaControlada());
        
        painelListagem.setLayout(new GridBagLayout());
        
        painelListagem.setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,50,0);
        
        painelListagem.add(listarProdutos, c);
        
        c.insets = new Insets(0,0,10,0);
        
        c.gridy = 1;
        
        painelListagem.add(listaNomesProdutos, c);
        
        c.gridy = 2;
        
        painelListagem.add(voltarListar, c);
        
        voltarListar.addActionListener(gerenciador);
        
    }

    public void ligarAdicionar(){
    
        container.add(painelAdicionar);
        
        setVisible(true);
    
    }
    
    public void ligarListagem(){
    
        container.add(painelListagem);
        
        setVisible(true);
    
    }
    
    public void desligarListagem(){
    
        container.remove(painelListagem);
        
        setVisible(false);
    
    }
    
    public void desligarAdicionar(){
    
        container.remove(painelAdicionar);
        
        setVisible(false);
    
    }
    
    public void desligar(){
    
        setVisible(false);
    
    }
    
    
    
    public void adicionarProduto(){
        
        String nomeTeste = inserirNome.getText();
        
        String precoTeste = inserirPreco.getText();
        
        try{
            
            String nome = receberTexto(nomeTeste);
            
            int preco = receberValorInteiro(precoTeste);
            
            TelaRifaNaoFinalizada.getInstancia().adicionarProduto(nome, preco);
            
            JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso");
            
            desligarAdicionar();
            
            TelaRifaNaoFinalizada.getInstancia().ligar();

        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
    
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
  
            CtrlRifa.getInstancia().realizarAcaoTelaProduto(ae.getActionCommand());
            
        }

    }
    
}
