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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author budi
 */
public class TelaApostadorIniciado extends Tela{
    
    private static TelaApostadorIniciado instancia;
    
    private JLabel nomeApostadorMenu;
    
    private JLabel nomeApostadorAssociando;
    
    private JLabel nomeApostadorDeletando;
    
    private JLabel codigoRifa;
    
    private JTextField inserirCodigoRifa;
    
    private JButton botaoAssociarRifaMenu;
    
    private JButton botaoAssociarRifaPanel;
    
    private JButton botaoDeletarApostadorMenu;
    
    private JButton botaoDeletarApostadorPanel;
    
    private JButton botaoVoltarMenu;
    
    private JButton botaoVoltarAssociar;
     
    private JButton botaoVoltarDeletar;
    
    private JPanel painelMenu;
    
    private JPanel painelAssociar;
    
    private JPanel painelDeletar;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaApostadorIniciado(){
    
        super("Apostador");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        iniciarComponentesMenu();
        
        iniciarComponentesAssociar();
        
        iniciarComponentesDeletar();
        
    }
    
    public static TelaApostadorIniciado getInstancia(){
    
        if(instancia == null){

                instancia = new TelaApostadorIniciado();

            }

        return instancia;

    }

    public void iniciarComponentesMenu(){
    
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
        
            nomeApostadorMenu = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador());
    
        }
        
        botaoAssociarRifaMenu = new JButton("Associar Com Rifa");
        
        botaoDeletarApostadorMenu = new JButton("Deletar Apostador");
        
        botaoVoltarMenu = new JButton("Voltar");
        
        painelMenu = new JPanel(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelMenu.add(botaoAssociarRifaMenu, c);
        
        c.gridy = 2;
        
        painelMenu.add(botaoDeletarApostadorMenu, c);
        
        c.gridy = 3;
        
        painelMenu.add(botaoVoltarMenu, c);
        
        botaoAssociarRifaMenu.addActionListener(gerenciador);
        
        botaoDeletarApostadorMenu.addActionListener(gerenciador);
        
        botaoVoltarMenu.addActionListener(gerenciador);
        
    }
    
    private void iniciarComponentesAssociar(){
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
    
            nomeApostadorAssociando = new JLabel("Associando Apostador " + CtrlApostador.getInstancia().getNomeApostador() + "Com Rifa");
        
        }
        
        codigoRifa = new JLabel("Insira o codigo Da Rifa: ");
        
        inserirCodigoRifa = new JTextField(10);
        
        botaoAssociarRifaPanel = new JButton("Associar com Rifa");
        
        botaoVoltarAssociar = new JButton("Voltar");
        
        botaoVoltarAssociar.setActionCommand("Voltar Painel Associar");
        
        painelAssociar = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelAssociar.add(nomeApostadorAssociando, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelAssociar.add(codigoRifa, c);
        
        c.gridx = 1;
        
        painelAssociar.add(inserirCodigoRifa, c);
        
        c.gridx = 0;
        
        c.gridy = 2;
        
        painelAssociar.add(botaoAssociarRifaPanel, c);
        
        c.gridy = 3;
        
        painelAssociar.add(botaoVoltarAssociar, c);
        
        inserirCodigoRifa.addActionListener(gerenciador);
        
        botaoAssociarRifaPanel.addActionListener(gerenciador);
        
        botaoVoltarAssociar.addActionListener(gerenciador);
        
    }
    
    private void iniciarComponentesDeletar(){
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
            
            nomeApostadorDeletando = new JLabel("Deletando Apostador " + CtrlApostador.getInstancia().getNomeApostador());
    
        }
        
        
    
    }
    
    public void atualizarNomeApostador(){
    
        painelMenu.remove(nomeApostadorMenu);
        
        painelAssociar.remove(nomeApostadorAssociando);
        
        painelDeletar.remove(nomeApostadorDeletando);
        
        nomeApostadorMenu = new JLabel("Menu do Apostador " + CtrlApostador.getInstancia().getNomeApostador());
        
        nomeApostadorAssociando = new JLabel("Associando Apostador " + CtrlApostador.getInstancia().getNomeApostador() + "Com Rifa");
        
        nomeApostadorDeletando = new JLabel("Deletando Apostador " + CtrlApostador.getInstancia().getNomeApostador());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        painelAssociar.add(nomeApostadorAssociando, c);
        
        painelDeletar.add(nomeApostadorDeletando, c);
    
    }


    public void ligarPainelMenu() {
        
        container.add(painelMenu);
    
        setVisible(true);
        
    }
    
    public void desligarPainelMenu(){
        
        container.remove(painelMenu);
    
        setVisible(false);
    
    }
    
    public void ligarPainelAssociar(){
    
        container.add(painelAssociar);
        
        setVisible(true);
    
    }
    
    public void desligarPainelAssociar(){
    
        container.remove(painelAssociar);
        
        setVisible(false);
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            CtrlApostador.getInstancia().realizaAcaoApostadorIniciado(ae.getActionCommand());
              
        }

    }
    
    
}