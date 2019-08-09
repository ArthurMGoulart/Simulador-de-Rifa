/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import br.ufsc.ine5605.rifa.Controles.CtrlApostador;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author budi
 */
public class TelaApostadorComprando extends Tela{
    
    private static TelaApostadorComprando instancia;
    
    private JLabel nomeApostadorMenu;
    
    private JLabel nomeApostadorComprando;
    
    private JLabel nomeApostadorListandoNumerosDisponiveis;
    
    private JLabel nomeApostadorListandoNumerosComprados;
    
    private JLabel numeroParaSerComprado;
    
    private JTextField inserirNumero;
    
    private Botao botaoComprarNumeroMenu;
    
    private Botao botaoListarNumerosDisponiveis;
    
    private Botao botaoListarNumerosComprados;
    
    private Botao botaoVoltarMenu;
    
    private Botao botaoComprarNumeroPainel;
    
    private Botao botaoVoltarComprar;
     
    private Botao botaoVoltarListarNumerosDisponiveis;
    
    private Botao botaoVoltarListarNumerosComprados;
    
    private JList listaNumerosDisponiveis;
    
    private JList listaNumerosComprados;
    
    private JPanel painelMenu;
    
    private JPanel painelComprar;
    
    private JPanel painelListarNumerosDisponiveis;
    
    private JPanel painelListarNumerosComprados;
    
    private Container container;   
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaApostadorComprando(){
    
        super("Apostador Comprando");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        iniciarComponentesMenu();
        
        iniciarComponentesComprar();
        
        iniciarComponentesListarNumerosDisponiveis();
        
        iniciarComponentesListarNumerosComprados();
    
    }
    
    public static TelaApostadorComprando getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaApostadorComprando();
        
        }
        
        return instancia;
    
    }

    private void iniciarComponentesMenu() {
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
        
            nomeApostadorMenu = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador());
    
        }
        
        botaoComprarNumeroMenu = new Botao("Comprar um Numero", AcoesBotao.ComprarNumeroMenu);
        
        botaoListarNumerosDisponiveis = new Botao("Listar Numeros Disponiveis", AcoesBotao.ListarNumerosDisponiveis);
        
        botaoListarNumerosComprados = new Botao("Listar Numeros Comprados", AcoesBotao.ListarNumerosComprados);
        
        botaoVoltarMenu = new Botao("Voltar", AcoesBotao.VoltarApostadorComprandoMenu);
        
        painelMenu = new JPanel(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelMenu.add(botaoComprarNumeroMenu, c);
        
        c.gridy = 2;
        
        painelMenu.add(botaoListarNumerosDisponiveis, c);
        
        c.gridy = 3;
        
        painelMenu.add(botaoListarNumerosComprados, c);
        
        c.gridy = 4;
        
        painelMenu.add(botaoVoltarMenu, c);
        
        botaoComprarNumeroMenu.addActionListener(gerenciador);
        
        botaoListarNumerosDisponiveis.addActionListener(gerenciador);
        
        botaoListarNumerosComprados.addActionListener(gerenciador);
        
        botaoVoltarMenu.addActionListener(gerenciador);
        
    }

    private void iniciarComponentesComprar() {
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
        
            nomeApostadorComprando = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador() + " Comprando Numero");
    
        }
        
        numeroParaSerComprado = new JLabel("Insira o Numero Que deseja Comprar: ");
        
        inserirNumero = new JTextField(10);
        
        botaoComprarNumeroPainel = new Botao("Comprar Numero", AcoesBotao.ComprarNumeroPainel);
        
        painelComprar = new JPanel(new GridBagLayout());
        
        botaoVoltarComprar = new Botao("Voltar", AcoesBotao.VoltarApostadorComprandoNumero);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelComprar.add(nomeApostadorComprando, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelComprar.add(numeroParaSerComprado, c);
        
        c.gridx = 1;
                
        painelComprar.add(inserirNumero, c);
        
        c.gridy = 2;
        
        painelComprar.add(botaoComprarNumeroPainel, c);
        
        c.gridy = 3;
        
        painelComprar.add(botaoVoltarComprar, c);
        
        botaoComprarNumeroPainel.addActionListener(gerenciador);
        
        botaoVoltarComprar.addActionListener(gerenciador);
        
    }

    private void iniciarComponentesListarNumerosDisponiveis() {
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
        
            nomeApostadorListandoNumerosDisponiveis = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador()
                    
            + " Listando Numeros Disponiveis da Rifa: " + 
                    
            CtrlApostador.getInstancia().getApostadorControlado().getRifaAssociada().getRifa().getCodigo());
    
        }
        
        painelListarNumerosDisponiveis = new JPanel(new GridBagLayout());
        
        listaNumerosDisponiveis = new JList(CtrlApostador.getInstancia().getNumerosDisponiveisVendaRifa());
        
        listaNumerosDisponiveis.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        
        painelListarNumerosDisponiveis.setSize(700,320);
        
        botaoVoltarListarNumerosDisponiveis = new Botao("Voltar", AcoesBotao.VoltarApostadorComprandoListandoDisponiveis);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelListarNumerosDisponiveis.add(nomeApostadorListandoNumerosDisponiveis, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelListarNumerosDisponiveis.add(listaNumerosDisponiveis, c);
        
        c.gridy = 2;
        
        painelListarNumerosDisponiveis.add(botaoVoltarListarNumerosDisponiveis, c);
        
        botaoVoltarListarNumerosDisponiveis.addActionListener(gerenciador);
        
    }

    private void iniciarComponentesListarNumerosComprados() {
        
        if(CtrlApostador.getInstancia().getApostadorControlado() != null){
        
            nomeApostadorListandoNumerosComprados = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador()
                    
            + " Listando Numeros Comprados da Rifa: " + 
                    
            CtrlApostador.getInstancia().getApostadorControlado().getRifaAssociada().getRifa().getCodigo());
    
        }
        
        listaNumerosComprados = new JList(CtrlApostador.getInstancia().getNumerosComprados());
        
        JScrollPane sp = new JScrollPane(listaNumerosComprados);

        listaNumerosComprados.setVisibleRowCount(10);  
        
        painelListarNumerosComprados = new JPanel(new GridBagLayout());
        
        botaoVoltarListarNumerosComprados = new Botao("Voltar", AcoesBotao.VoltarApostadorComprandoListandoComprados);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelListarNumerosComprados.add(nomeApostadorListandoNumerosComprados, c);
        
        c.insets = new Insets(0,0,15,0);
        
        c.gridy = 1;
        
        painelListarNumerosComprados.add(listaNumerosComprados, c);
        
        c.gridy = 2;
        
        painelListarNumerosComprados.add(botaoVoltarListarNumerosComprados, c);
        
        botaoVoltarListarNumerosComprados.addActionListener(gerenciador);
    
    }
    
    public void atualizarInformacoes(){
    
        painelMenu.remove(nomeApostadorMenu);
        
        painelComprar.remove(nomeApostadorComprando);
        
        painelListarNumerosDisponiveis.remove(nomeApostadorListandoNumerosDisponiveis);
        
        painelListarNumerosComprados.remove(nomeApostadorListandoNumerosComprados);
        
        painelListarNumerosDisponiveis.remove(listaNumerosDisponiveis);
        
        painelListarNumerosComprados.remove(listaNumerosComprados);
        
        nomeApostadorMenu = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador());
        
        nomeApostadorComprando = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador() + " Comprando Numero");
                
        nomeApostadorListandoNumerosDisponiveis = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador()
                    
        + " Listando Numeros Disponiveis da Rifa: " + 
                    
        CtrlApostador.getInstancia().getApostadorControlado().getRifaAssociada().getRifa().getCodigo());
        
        nomeApostadorListandoNumerosComprados = new JLabel("Apostador " + CtrlApostador.getInstancia().getNomeApostador()
                    
        + " Listando Numeros Comprados da Rifa: " + 
                    
        CtrlApostador.getInstancia().getApostadorControlado().getRifaAssociada().getRifa().getCodigo());
        
        listaNumerosDisponiveis = new JList(CtrlApostador.getInstancia().getNumerosDisponiveisVendaRifa());
        
        listaNumerosDisponiveis.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        
        listaNumerosComprados = new JList(CtrlApostador.getInstancia().getNumerosComprados());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0,0,40,0);
        
        painelMenu.add(nomeApostadorMenu, c);
        
        painelComprar.add(nomeApostadorComprando, c);
        
        painelListarNumerosDisponiveis.add(nomeApostadorListandoNumerosDisponiveis, c);
        
        c.gridy = 1;
        
        painelListarNumerosDisponiveis.add(listaNumerosDisponiveis, c);
        
        c.gridy = 0;
        
        painelListarNumerosComprados.add(nomeApostadorListandoNumerosComprados, c);
        
        c.gridy = 1;
        
        painelListarNumerosComprados.add(listaNumerosComprados, c);
    
    }
    
    public void ligarPainelMenu(){
        
        container.add(painelMenu);
        
        setVisible(true);
    
    }
    
    public void ligarPainelComprar(){
        
        container.add(painelComprar);
        
        setVisible(true);
    
    }
    
    public void ligarPainelListarNumerosDisponiveis(){
        
        container.add(painelListarNumerosDisponiveis);
        
        setVisible(true);
    
    }
    
    public void ligarPainelListarNumerosComprados(){
        
        container.add(painelListarNumerosComprados);
        
        setVisible(true);
    
    }
    
    public void desligarPainelMenu(){

        container.remove(painelMenu);
        
        setVisible(false);

    }
    
    public void desligarPainelComprar(){

        container.remove(painelComprar);
        
        setVisible(false);

    }
    
    public void desligarPainelListarNumerosDisponiveis(){

        container.remove(painelListarNumerosDisponiveis);
        
        setVisible(false);

    }
    
    public void desligarPainelListarNumerosComprados(){

        container.remove(painelListarNumerosComprados);
        
        setVisible(false);

    }

    public void iniciarComprarNumero() {
        
        String numeroTeste = inserirNumero.getText();
        
        try{
        
            int numero = receberValorInteiro(numeroTeste);
            
            CtrlApostador.getInstancia().addNumero(numero);
            
            JOptionPane.showMessageDialog(null, "Numero: " + numero + " Comprado Com Sucesso");
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            Botao teste = (Botao) ae.getSource();
            
            CtrlApostador.getInstancia().realizaAcaoApostadorComprando(teste.getAcao());
              
        }

    }
    
}
