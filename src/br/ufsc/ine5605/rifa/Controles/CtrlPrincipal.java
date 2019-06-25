/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Telas.TelaCriarRifa;
import br.ufsc.ine5605.rifa.Telas.TelaPrincipal;
import java.io.Serializable;
/**
 *
 * @author budi
 */
public class CtrlPrincipal implements Serializable{

    private static CtrlPrincipal instancia;
    
    private CtrlPrincipal(){
        
        TelaPrincipal.getInstancia().ligar();
    
    }
    
    public static CtrlPrincipal getInstancia(){
        
        if(instancia == null){
        
            instancia = new CtrlPrincipal();
        
        }
        
        return instancia;
        
    }
    
    public void realizaAcao(String acao){
        
        switch(acao){
        
            case "Criar Rifa":
                
                this.desligarTela();
                
                CtrlRifa.getInstancia().iniciarTelaCriarRifa();
                
                break;
                
            case "Acessar Rifa":
                
                this.desligarTela();
                
                CtrlRifa.getInstancia().iniciarTelaAcessarRifa();
                
                break;
                
            case "Criar Apostador":
                
                this.desligarTela();
                
                CtrlApostador.getInstancia().iniciarTelaCriarApostador();
                
                break;
                
            case "Acessar Apostador":
                
                this.desligarTela();
                
                CtrlApostador.getInstancia().iniciarTelaAcessarApostador();
                
                break;
                
        }
    
    }
    
    public void ligarTela(){
    
        TelaPrincipal.getInstancia().ligar();
    
    }

    public void desligarTela() {
        
        TelaPrincipal.getInstancia().desligar();
        
    }

    public CtrlRifa getControladorRifa() {
       
        return CtrlRifa.getInstancia();
        
    }
    
}