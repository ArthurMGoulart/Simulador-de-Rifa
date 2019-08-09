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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author budi
 */
public class TelaRifaFinalizada extends Tela{
    
    private static TelaRifaFinalizada instancia;
    
    private JLabel informacoesRifa;
    
    private Botao sortearProduto;
    
    private Botao listarApostadoresGanhadores;
    
    private Botao voltar;
    
    private Container container;
    
    private GerenciadorBotoes gerenciador = new GerenciadorBotoes();
    
    private TelaRifaFinalizada(){
        
        super("Tela Rifa Finalizada");
        
        iniciarComponentes();
        
    }
    
    public static TelaRifaFinalizada getInstancia(){
    
        if(instancia == null){
            
            instancia = new TelaRifaFinalizada();
        
        }
        
        return instancia;
    
    }
    
    private void iniciarComponentes(){
    
        informacoesRifa = new JLabel("Rifa " + CtrlRifa.getInstancia().getRifaControlada().getCodigo()
        
        + " Lucro Atual: " + CtrlRifa.getInstancia().getRifaControlada().getLucro());
        
        sortearProduto = new Botao("Sortear Produto", AcoesBotao.SortearProduto);
        
        listarApostadoresGanhadores = new Botao("Listar Apostadores Ganhadores", AcoesBotao.ListarApostadoresGanhadores);
        
        voltar = new Botao("Voltar", AcoesBotao.VoltarRifaFinalizada);
        
        container = getContentPane();
        
        container.setLayout(new GridBagLayout());
        
        setSize(700,320);

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,0,15,0);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        container.add(informacoesRifa, c);
        
        c.gridy = 1;
        
        container.add(sortearProduto, c);
        
        c.gridy = 2;
        
        container.add(listarApostadoresGanhadores, c);
        
        c.gridy = 3;
        
        container.add(voltar, c);
        
        sortearProduto.addActionListener(gerenciador);
        
        listarApostadoresGanhadores.addActionListener(gerenciador);
        
        voltar.addActionListener(gerenciador);
         
    }
    
    public void atualizarInformacoesRifa(){
    
        GridBagConstraints c = new GridBagConstraints();
        
        container.remove(informacoesRifa);
        
        informacoesRifa = new JLabel("Rifa " + CtrlRifa.getInstancia().getRifaControlada().getCodigo()
        
        + " Lucro Atual: " + CtrlRifa.getInstancia().getRifaControlada().getLucro());
        
        c.gridy = 0;
        
        c.insets = new Insets(0,0,20,0);
        
        c.fill = GridBagConstraints.HORIZONTAL;

        container.add(informacoesRifa);
    
    }
    
    public void ligar(){
    
        setVisible(true);
    
    }
    
    public void desligar(){
    
        setVisible(false);
    
    }

    public void iniciarSortearProduto() {
        
        try{
        
            String informacoes[] = CtrlRifa.getInstancia().sortearProduto();
            
            JOptionPane.showMessageDialog(null, "Numero Sorteado: " + informacoes[0] + " Produto Sorteado: " + informacoes[1]
            
            + " Apostador Ganhador: " + informacoes[2]);
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
        
    }
    
    private class GerenciadorBotoes implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            
            Botao teste = (Botao) ae.getSource();
            
            CtrlRifa.getInstancia().realizarAcaoRifaFinalizada(teste.getAcao());
            
        }

    }
     
}
