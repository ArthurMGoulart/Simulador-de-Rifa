/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

import java.util.InputMismatchException;

import java.util.Scanner;

/**
 *
 * @author budi
 */
public abstract class Tela {
    
    public Scanner entradaDeDados;
    
    public Tela(){
    
        this.entradaDeDados = new Scanner(System.in);
    
    }
    
    public String receberTexto() throws NumberFormatException{
    
        String linha = entradaDeDados.nextLine();
        
        if(eUmNumero(linha)){
        
            throw new NumberFormatException("Apenas letras sao aceitas neste campo");
            
        }else{
        
            return linha;
        
        }
       
    }
    
    public int receberValorInteiro() throws NumberFormatException{
               
        String linha;
            
        linha = entradaDeDados.nextLine();
            
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
    
    public void mensagemEnter(){
    
        System.out.println("Digite <ENTER> para continuar");
        
        entradaDeDados.nextLine();
    
    }
    
}