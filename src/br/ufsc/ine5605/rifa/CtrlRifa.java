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
public class CtrlRifa implements IControlador{
    
    private TelaRifa tela;
    
    private CtrlPrincipal controlador;
    
    private CtrlRifa controladorRifa;
    
    private Rifa rifaControlada;
    
    private BancoDeRifas bancoDeRifas;
    
    public CtrlRifa(CtrlPrincipal controlador){
        
        this.controlador = controlador;
        
        this.bancoDeRifas = new BancoDeRifas();
        
        this.tela = new TelaRifa(this);
          
    }

    public TelaRifa getTela() {
        
        return tela;
        
    }

    public CtrlPrincipal getControlador() {
        
        return controlador;
        
    }

    public CtrlRifa getControladorRifa() {
        
        return controladorRifa;
        
    }

    public BancoDeRifas getBancoDeRifas() {
        
        return bancoDeRifas;
        
    }

    public Rifa getRifaControlada() {
        
        return rifaControlada;
        
    }

    public void setRifaControlada(Rifa rifaControlada) {
        
        this.rifaControlada = rifaControlada;
        
    }
    
    public void iniciarTela() {
        
        this.tela.inicia();
        
    }
    
    public void iniciarCriarRifa(){
    
        this.tela.criarRifa();
    
    } 

    public void criarRifa(int codigo, int porcentagemDeLucro, int quantidadeDeNumerosParaVender) throws Exception{

        Rifa rifa = new Rifa(this, codigo, porcentagemDeLucro, quantidadeDeNumerosParaVender);
        
        if(this.bancoDeRifas.temRifaComCodigo(codigo)){
        
            throw new IllegalArgumentException("Rifa ja existente com esse codigo");
            
        }else if(porcentagemDeLucro < 0){
        
            throw new IllegalArgumentException("Porcentagem de Lucro menor que zero");
        
        }else if(quantidadeDeNumerosParaVender <= 0){
        
            throw new IllegalArgumentException("Quantidade De Numeros para Vender menor ou igual a zero");
        
        }
        
        this.bancoDeRifas.addRifa(rifa);

    }
    
    public void adicionarProduto(String nomeProduto, Integer precoProduto) throws IllegalArgumentException{
            
        this.rifaControlada.adicionarProduto(nomeProduto, precoProduto);
        
    }

    public void finalizarRifa() throws Exception{
        
        this.rifaControlada.setFinalizada(true);
        
        this.rifaControlada.finalizar();
        
        this.bancoDeRifas.removerRifa(rifaControlada);
        
        this.bancoDeRifas.addRifa(rifaControlada);
            
        this.rifaControlada.finalizarApostadores();
        
    }

    private void sortearProdutos() throws Exception{
    
        this.rifaControlada.sortearUmProduto();
        
    }

    public void associarApostador(Rifa rifaParaAssociar, Apostador apostador) throws Exception{
        
        rifaParaAssociar.serAssociadaComApostador(apostador);
        
    }
    
}