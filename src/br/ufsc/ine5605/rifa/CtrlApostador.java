/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

import java.util.ArrayList;

import java.util.Scanner;

/**
 *
 * @author budi
 */
public class CtrlApostador implements IControlador{
    
    private TelaApostador tela;
    
    private Apostador apostadorControlado;
    
    private CtrlPrincipal controlador;
    
    private BancoDeApostadores bancoDeApostadores;
    
    public CtrlApostador(CtrlPrincipal controlador){
        
        this.controlador = controlador;

        this.bancoDeApostadores = new BancoDeApostadores();
        
        this.tela = new TelaApostador(this); 
        
    }

    public Apostador getApostadorControlado() {
        
        return apostadorControlado;
        
    }

    public BancoDeApostadores getBancoDeApostadores(){
        
        return bancoDeApostadores;
        
    }

    public TelaApostador getTela() {
        
        return tela;
        
    } 

    public CtrlPrincipal getControlador() {
        
        return controlador;
        
    }
    
    public void setApostadorControlado(Apostador apostadorControlado) {
        
        this.apostadorControlado = apostadorControlado;
        
    }
    
    public void iniciarTela() {
        
        this.tela.inicia();
        
    }
    
    public void iniciarCriarApostador(){
    
        this.tela.criarApostador();
        
    }
    
    public void comprarNumero(int numero) throws IllegalArgumentException{
        
        this.apostadorControlado.comprarNumero(numero);
        
    }

    public void associarComRifa(int codigo) throws Exception{
        
        Rifa rifaParaAssociar = this.controlador.getControladorRifa().getBancoDeRifas().retornarRifaPorCodigo(codigo);
        
        if(rifaParaAssociar.isFinalizada()){
        
            throw new Exception("Rifa ja finalizada nao e possivel se associar");
        
        }
        
        this.apostadorControlado.associarRifa(rifaParaAssociar);
        
        this.controlador.getControladorRifa().associarApostador(rifaParaAssociar, this.apostadorControlado);
        
    }

    public void criarApostador(String nome, int cpf) throws IllegalArgumentException{
        
        if(nome == null){
        
            throw new IllegalArgumentException("Nome nulo");
            
        }else if(cpf <= 0){
        
            throw new IllegalArgumentException("Cpf menor que zero");
        
        }
        
        Apostador apostador = new Apostador(this,nome, cpf);
        
        if(this.bancoDeApostadores.temApostadorComCpf(cpf)){
        
            throw new IllegalArgumentException("Apostador ja existente com esse CPF");
         
        }else{
        
            this.bancoDeApostadores.adicionarApostador(apostador);
            
        }
        
    }

    public void deletarApostador() {
        
        if(this.apostadorControlado.getRifaAssociada() != null){
        
            this.apostadorControlado.getRifaAssociada().getRifa().deletarApostador(this.apostadorControlado);
        
        }
        
        this.bancoDeApostadores.deletarApostador(this.apostadorControlado);
        
    }

}