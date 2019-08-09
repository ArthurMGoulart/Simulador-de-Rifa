/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import javax.swing.JButton;

/**
 *
 * @author budi
 */
public class Botao extends JButton{
    
    AcoesBotao acao;
    
    public Botao(String texto, AcoesBotao acao){
        
        super(texto);
        
        this.acao = acao;

    }
    
    public AcoesBotao getAcao(){
    
        return acao;
    
    }
    
}