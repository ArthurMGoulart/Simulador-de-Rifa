/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

import java.util.ArrayList;

/**
 *
 * @author budi
 */
public class BancoDeApostadores {
    
    private ArrayList<Apostador> apostadores;
    
    public BancoDeApostadores(){
    
        apostadores = new ArrayList<>();
    
    }

    public ArrayList<Apostador> getApostadores() {
        
        return apostadores;
        
    } 
    
    public void adicionarApostador(Apostador apostador){
    
        apostadores.add(apostador);
    
    }
    
    public Apostador retornarApostadorPorCpf(int cpf) throws Exception{
        
        for(Apostador apostadorCheque : apostadores){
        
            if(apostadorCheque.getCpf() == cpf){
            
                return apostadorCheque;
            
            }
                 
        }
        
        throw new Exception("Nao existe apostador com esse cpf");
    
    }
    
    public boolean temApostadorComCpf(int cpf){
        
        for(Apostador apostadorCheque : apostadores){
        
            if(apostadorCheque.getCpf() == cpf){
            
                return true;
            
            }
                 
        }
        
        return false;
    
    }

    public void deletarApostador(Apostador apostador) {
       
        apostadores.remove(apostador);
        
    }
    
}