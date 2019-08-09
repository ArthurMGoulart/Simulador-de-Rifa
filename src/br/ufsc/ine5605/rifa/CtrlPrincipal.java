/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

import java.util.Scanner;

/**
 *
 * @author budi
 */
public class CtrlPrincipal implements IControlador{
    
    private CtrlRifa controladorRifa;
    
    private CtrlApostador controladorApostador;
    
    private TelaPrincipal tela;
    
    public CtrlPrincipal(){
        
        this.controladorRifa = new CtrlRifa(this);
        
        this.controladorApostador = new CtrlApostador(this);
        
        this.tela = new TelaPrincipal(this);
        
        this.iniciarTela();
    
    }

    public CtrlRifa getControladorRifa() {
        
        return controladorRifa;
        
    }

    public CtrlApostador getControladorApostador() {
        
        return controladorApostador;
        
    }

    public TelaPrincipal getTela() {
        
        return tela;
        
    }   
    
    public void iniciarTela(){
    
        this.tela.inicia();
    
    }
    
    public void iniciarCriarRifa() throws IllegalArgumentException{
    
        this.controladorRifa.iniciarCriarRifa();
    
    }
    
    public void iniciarCriarApostador() {
       
        this.controladorApostador.iniciarCriarApostador();
        
    }

    public void acessarRifa(int codigo) throws Exception{
             
        this.controladorRifa.setRifaControlada(this.controladorRifa.getBancoDeRifas().retornarRifaPorCodigo(codigo));
        
        this.controladorRifa.iniciarTela();
           
    }

    public void acessarApostador(int cpf) throws Exception{

        this.controladorApostador.setApostadorControlado(this.controladorApostador.getBancoDeApostadores().retornarApostadorPorCpf(cpf));
        
        this.controladorApostador.iniciarTela();
        
    }
        
}