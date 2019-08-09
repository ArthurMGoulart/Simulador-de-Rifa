/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Entidades;

import br.ufsc.ine5605.rifa.Entidades.Pessoa;
import br.ufsc.ine5605.rifa.Entidades.Produto;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import br.ufsc.ine5605.rifa.Controles.CtrlApostador;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author budi
 */
public class Apostador extends Pessoa implements Serializable{
    
    private ArrayList<Integer> numerosComprados;
    
    private AssocRifaApostador rifaAssociada;
    
    private EstadoApostador estado;
       
    private ArrayList<Produto> produtosGanhos;

    public Apostador(String nome, int cpf) {
        
        super(nome,cpf);
        
        this.estado = EstadoApostador.Iniciado;
        
        this.numerosComprados = new ArrayList<>();
        
        this.produtosGanhos = new ArrayList<>();
        
    }

    public AssocRifaApostador getRifaAssociada() {
        
        return rifaAssociada;
        
    }

    public String[] getNomeProdutosGanhos() {
        
        String[] nomes = new String[produtosGanhos.size()];
        
        int i = 0;
        
        for(Produto produto : produtosGanhos){
        
            nomes[i] = produto.getNome();
            
            i++;
        
        }
        
        return nomes;
        
    }
    
    public Integer getQuantidadeDeNumero(){
    
        return this.numerosComprados.size();
    
    }

    public EstadoApostador getEstado() {
        
        return estado;
        
    }

    public void setEstado(EstadoApostador estado) {
        
        this.estado = estado;
        
    }

    public void comprarNumero(Integer numero) throws IllegalArgumentException{
          
        if(temEsseNumero(numero) || numero == null){

            throw new IllegalArgumentException("numero Ja adquirido");

        }

        if(!this.rifaAssociada.getRifa().temNumeroDisponivelParaVender(numero)){
            
            throw new IllegalArgumentException("Numero ja vendido pela Rifa");
        
        }
          
        numerosComprados.add(numero);
        
    }      
    
    public boolean temEsseNumero(Integer numero){
    
        for(Integer numeroCheque : numerosComprados){
        
            if(numeroCheque == numero){
            
                return true;
            
            }
        
        }
        
        return false;
    
    }

    public void associarRifa(Rifa rifaParaAssociar) {
       
        if(rifaAssociada != null){
        
            this.rifaAssociada.getRifa().desassociarApostador(this);
            
        }
        
        AssocRifaApostador apostadorAssociado = new AssocRifaApostador(rifaParaAssociar, this);
        
        this.rifaAssociada = apostadorAssociado;
        
    }

    public double calcularLucro() {
         
        double lucro = 0;
        
        for(Produto produto : produtosGanhos){
        
            lucro += produto.getPreco();
        
        }
        
        lucro = lucro - this.getQuantidadeDeNumero() * this.rifaAssociada.getRifa().getPrecoPorNumero();
        
        return lucro;
        
    }

    public void receberProdutoGanho(Produto produtoGanho) {
        
        this.produtosGanhos.add(produtoGanho);
        
        this.setEstado(EstadoApostador.Ganhador);
        
    }
    
    public boolean eGanhador(){
    
        if(this.produtosGanhos.isEmpty()){
        
            return false;
            
        }
        
        return true;
    
    }
    
    public String[] getNumerosComprados(){
        
        String[] numeros = new String[this.numerosComprados.size()];
        
        int i = 0;
    
        for(Integer numeroTeste : numerosComprados){
        
            numeros[i] = Integer.toString(numeroTeste);
            
            i++;
        
        }
        
        return numeros;

    }
    
}