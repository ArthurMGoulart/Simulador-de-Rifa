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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author budi
 */
public class TelaListarApostadoresDaRifa extends Tela implements Serializable{
    
    private static TelaListarApostadoresDaRifa instancia;
    
    private JList apostadores;
    
    private JList apostadoresGanhadores;
    
    private Botao voltarListagem;
    
    private Botao voltarListagemGanhadores;
    
    private JPanel listarApostadores;
    
    private JPanel listarApostadoresGanhadores;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaListarApostadoresDaRifa(){
    
        super("Listando Apostadores");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        iniciarComponentesListagemApostadores();
        
        iniciarComponentesListagemApostadoresGanhadores();
    
    }
    
    public static TelaListarApostadoresDaRifa getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaListarApostadoresDaRifa();
        
        }
        
        return instancia;
    
    }
    
    public void iniciarComponentesListagemApostadores(){
        
        apostadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresDaRifa());
        
        voltarListagem = new Botao("Voltar", AcoesBotao.VoltarListagemApostadores);
        
        listarApostadores = new JPanel(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        listarApostadores.add(apostadores, c);

        c.gridy = 1;
        
        listarApostadores.add(voltarListagem, c);
        
        voltarListagem.addActionListener(gerenciador);
    
    }
    
    public void iniciarComponentesListagemApostadoresGanhadores(){
    
        apostadoresGanhadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresGanhadoresDaRifa());
        
        voltarListagemGanhadores = new Botao("Voltar", AcoesBotao.VoltarListagemApostadoresGanhadores);
        
        listarApostadoresGanhadores = new JPanel(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        listarApostadoresGanhadores.add(apostadoresGanhadores, c);

        c.gridy = 1;
        
        listarApostadoresGanhadores.add(voltarListagemGanhadores, c);
        
        voltarListagemGanhadores.addActionListener(gerenciador);
    
    }
    
    public void atualizarListagemApostadores(){
    
        listarApostadores.remove(apostadores);
        
        apostadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresDaRifa());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        
        listarApostadores.add(apostadores, c);
    
    }
    
    public void atualizarListagemApostadoresGanhadores(){
    
        listarApostadoresGanhadores.remove(apostadoresGanhadores);
        
        apostadoresGanhadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresGanhadoresDaRifa());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        
        listarApostadoresGanhadores.add(apostadoresGanhadores, c);
    
    }
    
    public void ligarListagemApostadores(){
    
        container.add(listarApostadores);
        
        setVisible(true);
    
    }
    
    public void ligarListagemApostadoresGanhadores(){
    
        container.add(listarApostadoresGanhadores);
        
        setVisible(true);
        
    }
    
    public void desligarListagemApostadores(){
    
        container.remove(listarApostadores);
        
        setVisible(false);
        
    }
    
    public void desligarListagemApostadoresGanhadores(){
    
        container.remove(listarApostadoresGanhadores);
        
        setVisible(false);
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            Botao teste = (Botao) ae.getSource();
            
            CtrlRifa.getInstancia().realizaAcaoTelaListarApostadores(teste.getAcao());
            
        }

    }
    
}
