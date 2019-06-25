/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Entidades;

import br.ufsc.ine5605.rifa.Entidades.Apostador;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import java.io.Serializable;

/**
 *
 * @author budi
 */
public class AssocRifaApostador implements Serializable{
    
    private Apostador apostador;
    
    private Rifa rifa;
    
    public AssocRifaApostador(Rifa rifa, Apostador apostador) {
        
        this.rifa = rifa;
        
        this.apostador = apostador;
        
    }

    public Apostador getApostador() {
        
        return apostador;
        
    }

    public Rifa getRifa() {
        
        return rifa;
        
    }
         
}