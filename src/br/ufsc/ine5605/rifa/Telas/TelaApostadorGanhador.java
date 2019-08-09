/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlApostador;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author budi
 */
public class TelaApostadorGanhador extends Tela{
    
    private static TelaApostadorGanhador instancia;
    
    private JLabel nomeApostadorMenu;
    
    private JLabel nomeApostadorListagem;
    
    private Botao listarMenu;
    
    private Botao voltarMenu;
    
    private Botao voltarListagem;
    
    private JList listaProdutosGanhos;
    
    private JPanel painelMenu;
    
    private JPanel painelListagem;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaApostadorGanhador(){
    
        super("Apostador Ganhador");
        
        container = getContentPane();
        
        setSize(700,320);
        
        iniciarComponentesMenu();
        
        iniciarComponentesListagem();
    
    }
    
    public static TelaApostadorGanhador getInstancia(){
        
        if(instancia == null){
        
            instancia = new TelaApostadorGanhador();
        
        }
        
        return instancia;
    
    }
    
    public void iniciarComponentesMenu(){
        
        nomeApostadorMenu = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador());
        
        listarMenu = new Botao("Listar Produtos Ganhos", AcoesBotao.ListarProdutosGanhos);
        
        voltarMenu = new Botao("Voltar", AcoesBotao.VoltarApostadorComprandoMenu);
        
        painelMenu = new JPanel(new GridBagLayout());
        
        painelMenu.setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        c.insets = new Insets(0,0,15,0);
    
        c.gridy = 1;
        
        painelMenu.add(listarMenu, c);
        
        c.gridy = 2;
        
        painelMenu.add(voltarMenu, c);
        
        listarMenu.addActionListener(gerenciador);
        
        voltarMenu.addActionListener(gerenciador);
        
    
    }
    
    public void iniciarComponentesListagem(){
        
        nomeApostadorListagem = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador() + "Listando Produtos Ganhos"); 
    
        voltarListagem = new Botao("Voltar", AcoesBotao.VoltarListagemApostadorGanhador);
        
        listaProdutosGanhos = new JList(CtrlApostador.getInstancia().getProdutosGanhos());
        
        painelListagem = new JPanel(new GridBagLayout());
        
        painelListagem.setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelListagem.add(nomeApostadorListagem, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelListagem.add(listaProdutosGanhos, c);
        
        c.gridy = 2;
        
        painelListagem.add(voltarListagem, c);
        
        voltarListagem.addActionListener(gerenciador);
   
    }
    
    public void atualizarInformacoes(){
    
        painelMenu.remove(nomeApostadorMenu);
        
        painelListagem.remove(nomeApostadorListagem);
    
        painelListagem.remove(listaProdutosGanhos);
        
        nomeApostadorMenu = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador());
        
        nomeApostadorListagem = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador() + "Listando Produtos Ganhos");
        
        listaProdutosGanhos = new JList(CtrlApostador.getInstancia().getProdutosGanhos());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        painelListagem.add(nomeApostadorListagem, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelListagem.add(listaProdutosGanhos, c);
        
    }
    
    public void ligarPainelMenu(){
        
        container.add(painelMenu);
        
        setVisible(true);
    
    }
    
    public void ligarPainelListagem(){
        
        container.add(painelListagem);
                
        setVisible(true);
    
    }
    
    public void desligarPainelMenu(){
    
        container.remove(painelMenu);
        
        setVisible(false);
        
    }
    
    public void desligarPainelListagem(){
        
        container.remove(painelListagem);
        
        setVisible(false);
    
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            Botao teste = (Botao) ae.getSource();
            
            CtrlApostador.getInstancia().realizaAcaoApostadorGanhador(teste.getAcao());
              
        }

    }
    
    
}
