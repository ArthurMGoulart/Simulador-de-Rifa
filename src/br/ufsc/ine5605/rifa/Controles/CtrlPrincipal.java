/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Telas.AcoesBotao;
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
    
    public void realizaAcao(AcoesBotao acao){
        
        if(acao.equals(AcoesBotao.CriarRifaMenu)){
        
            this.desligarTela();
                
            CtrlRifa.getInstancia().iniciarTelaCriarRifa();
            
        }else if(acao.equals(AcoesBotao.AcessarRifaMenu)){
        
            this.desligarTela();
                
            CtrlRifa.getInstancia().iniciarTelaAcessarRifa();
        
        }else if(acao.equals(AcoesBotao.CriarApostadorMenu)){
        
            this.desligarTela();
                
            CtrlApostador.getInstancia().iniciarTelaCriarApostador();
        
        }else if(acao.equals(AcoesBotao.AcessarApostadorMenu)){
        
            this.desligarTela();
                
            CtrlApostador.getInstancia().iniciarTelaAcessarApostador();
        
        }else if(acao.equals(AcoesBotao.Encerrar)){
        
            persistirRifa();
            
            persistirApostador();
            
            System.exit(0);
        
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

    public void persistirRifa() {
        
        CtrlRifa.getInstancia().getMapeadorRifa().persist();
        
    }
    
    public void persistirApostador(){
    
        CtrlApostador.getInstancia().getMapeadorApostador().persist();
    
    }
    
    
}