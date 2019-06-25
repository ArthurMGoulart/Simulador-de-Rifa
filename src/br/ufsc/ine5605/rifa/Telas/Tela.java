/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author budi
 */
public abstract class Tela extends JFrame implements Serializable{
    
    public Scanner entradaDeDados;
    
    public Tela(String guia){
        
        super(guia);
        
        this.entradaDeDados = new Scanner(System.in);
    
    }
    
    public String receberTexto(String linha) throws NumberFormatException{
        
        if(eUmNumero(linha)){
        
            throw new NumberFormatException("Apenas letras sao aceitas neste campo");
            
        }else{
        
            return linha;
        
        }
       
    }
    
    public int receberValorInteiro(String linha) throws NumberFormatException{
            
        if(!eUmNumero(linha)){
        
            throw new NumberFormatException("Apenas numeros sao aceitos neste campo");
            
        }else{
        
            int valor = Integer.parseInt(linha);
            
            return valor;
        
        }
    
    }
    
    public boolean eUmNumero(String texto){
    
        try{
        
            Integer.parseInt(texto);
            
            return true;
        
        }catch(NumberFormatException e){
        
            return false;
            
        }

    }
    
}