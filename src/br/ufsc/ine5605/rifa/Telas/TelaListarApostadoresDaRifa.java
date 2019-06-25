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
import javax.swing.JButton;
import javax.swing.JList;

/**
 *
 * @author budi
 */
public class TelaListarApostadoresDaRifa extends Tela implements Serializable{
    
    private static TelaListarApostadoresDaRifa instancia;
    
    private JList apostadores;
    
    private JButton voltar;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaListarApostadoresDaRifa(){
    
        super("Listando Apostadores");
        
        iniciarComponentes();
    
    }
    
    public static TelaListarApostadoresDaRifa getInstancia(){
    
        if(instancia == null){
        
            instancia = new TelaListarApostadoresDaRifa();
        
        }
        
        return instancia;
    
    }
    
    public void iniciarComponentes(){
        
        apostadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresDaRifa());
        
        voltar = new JButton("Voltar");
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);
        
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 1;
        
        container.add(voltar, c);
        
        voltar.addActionListener(gerenciador);
    
    }
    
    public void atualizarListagem(){
    
        container.remove(apostadores);
        
        apostadores = new JList(CtrlRifa.getInstancia().getNomesApostadoresDaRifa());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        
        container.add(apostadores, c);
    
    }
    
    public void ligar(){
    
        setVisible(true);
    
    }
    
    public void desligar(){
    
        setVisible(false);
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
  
            TelaListarApostadoresDaRifa.getInstancia().desligar();
            
            CtrlRifa.getInstancia().iniciarTelaRifaNaoFinalizada();
            
        }

    }
    
}
