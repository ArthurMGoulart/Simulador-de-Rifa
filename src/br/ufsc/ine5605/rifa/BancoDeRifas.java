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
public class BancoDeRifas {
    
    private ArrayList<Rifa> rifas;
    
    public BancoDeRifas(){
    
        rifas = new ArrayList<>();
    
    }

    public ArrayList<Rifa> getRifas() {
        
        return rifas;
        
    } 
    
    public void addRifa(Rifa rifa) throws Exception{
    
        if(rifas.contains(rifa)){

            throw new Exception("Rifa ja incluida no banco");

        }
        
        rifas.add(rifa);
    
    }
    
    public void removerRifa(Rifa rifa){
    
        rifas.remove(rifa);
    
    }
    
    public boolean temRifaComCodigo(int codigo){
    
        for(Rifa rifaTeste : this.rifas){
        
            if(rifaTeste.getCodigo() == codigo){
                
                return true;
                
            }    
        
        }
    
        return false;
        
    }
    
    public Rifa retornarRifaPorCodigo(int codigo) throws Exception{
    
        for(Rifa rifaTeste : this.rifas){
        
            if(rifaTeste.getCodigo() == codigo){
                
                return rifaTeste;
                
            }    
        
        }
    
        throw new Exception("Nao Existe Rifa Com Esse Codigo");
        
    }
      
}