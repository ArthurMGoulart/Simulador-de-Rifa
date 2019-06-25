/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Entidades;

import br.ufsc.ine5605.rifa.Controles.CtrlRifa;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.Random;


/**
 *
 * @author budi
 */
public class Rifa implements Serializable{
    
    private Integer codigo;
    
    private ArrayList<AssocRifaApostador> apostadores;
    
    private ArrayList<Produto> produtos;
    
    private int quantidadeDeNumerosParaVender;
    
    private ArrayList<Integer> numerosParaVender;
    
    private ArrayList<Integer> numerosParaSortear;

    private int custo;
    
    private double precoPorNumero;
    
    private int porcentagemDeLucro;
    
    private Random gerador;
    
    private boolean finalizada = false;
    
    public Rifa(int codigo, int porcentagemDeLucro, int quantidadeDeNumerosParaVender) {
        
        this.codigo = codigo;
        
        this.quantidadeDeNumerosParaVender = quantidadeDeNumerosParaVender;
        
        this.apostadores = new ArrayList<>();
        
        this.produtos = new ArrayList<>();
        
        this.numerosParaSortear = new ArrayList<>();    
        
        this.numerosParaVender = new ArrayList<>();
        
        this.porcentagemDeLucro = porcentagemDeLucro;
        
        this.gerador = new Random();
        
    }

    public int getCodigo() {
        
        return codigo;
        
    }

    public double getPorcentagemDeLucro() {
        
        return porcentagemDeLucro;
        
    }

    public boolean isFinalizada() {
        
        return finalizada;
        
    }

    public void setFinalizada(boolean finalizada) {
        
        this.finalizada = finalizada;
        
    }  
    
    public int getQuantidadeDeNumerosParaVender() {
        
        return quantidadeDeNumerosParaVender;
        
    }
    
    public ArrayList<AssocRifaApostador> getApostadores() {
        
        return apostadores;
        
    }

    public ArrayList<Produto> getProdutos() {
        
        return produtos;
        
    }

    public ArrayList<Integer> getNumerosParaVender() {
        
        return numerosParaVender;
        
    }

    public int getCusto() {
        
        return custo;
        
    }

    public double getPrecoPorNumero() {
        
        return precoPorNumero;
        
    }

    public double getLucro() {
        
        return porcentagemDeLucro;
        
    }

    public void setCodigo(int codigo) {
        
        this.codigo = codigo;
        
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        
        this.produtos = produtos;
        
    }
 
    public void adicionarProduto(String nome, int preco) throws IllegalArgumentException{
                       
        if(nome == null || nome.equals("")){
            
            throw new IllegalArgumentException("Nome nulo do Produto");
            
        }else if(preco <= 0){
            
            throw new IllegalArgumentException("Preco invalido do Produto");
                 
        }
            
        Produto produto = new Produto(nome, preco);
            
        produtos.add(produto);
        
        CtrlRifa.getInstancia().getMapeadorRifa().persist();
                        
    }
    

    public void finalizar() {
        
        somarOsPrecosProduto();
        
        calcularPrecoPorNumero();
        
        definirNumerosParaVender();
    
    }
    
    public void definirNumerosParaVender(){
    
        for(int i = 0; i < this.quantidadeDeNumerosParaVender; i++){
        
            this.numerosParaVender.add(i);
            
        }
    
    }
    
    public void somarOsPrecosProduto(){
        
        for(Produto produto : produtos){
        
            this.custo += produto.getPreco();
        
        }
    
    }
    
    public void calcularPrecoPorNumero(){
    
        this.precoPorNumero = (double)((double)custo + ((double)custo * ((double)porcentagemDeLucro/100)))/(double)quantidadeDeNumerosParaVender;
    
    }

    public void serAssociadaComApostador(Apostador apostador) throws Exception{
        
        if(temApostadorComCpf(apostador.getCpf())){
        
            throw new Exception("Apostador Ja associado com Essa Rifa");
        
        }
        
        AssocRifaApostador apostadorAssociado = new AssocRifaApostador(this, apostador);
           
        this.apostadores.add(apostadorAssociado);
        
    }
    
    public boolean temNumeroDisponivelParaVender(int numero){
    
        for(int numeroTeste : numerosParaVender){
            
            if(numeroTeste == numero){
                
                return true;
                
            }
            
        }
        
        return false;
    
    }
    
    public void venderNumero(int numero) throws Exception{
    
        if(numero < 0){
        
            throw new Exception("Numero menor que a quantidade de Numeros");
        
        }else if(numero >= this.quantidadeDeNumerosParaVender){
        
            throw new IllegalArgumentException("Numero maior que a quantidade de Numeros");
        
        }else if(!temNumeroDisponivelParaVender(numero)){
        
            throw new Exception("Numero ja comprado");
        
        }
        
        this.numerosParaSortear.add(numero);
        
        this.numerosParaVender.remove(Integer.valueOf(numero));
        
    }
    
    public void sortearUmProduto() throws Exception{
        
        if(produtos.isEmpty()){
        
            throw new Exception("Todos os Produtos foram sorteados");
        
        }
        
        int numeroIndexAleatorio = gerador.nextInt(this.numerosParaSortear.size());
        
        int numeroSorteado = numerosParaSortear.get(numeroIndexAleatorio);

        Apostador apostadorGanhador = descobrirApostadorGanhador(numeroSorteado);
                
        Produto produtoSorteado = retornarProdutoComMenorPreco();
                
        apostadorGanhador.receberProdutoGanho(produtoSorteado);
        
        numerosParaSortear.remove(Integer.valueOf(numeroSorteado));
                
        produtos.remove(produtoSorteado);
                       
    }
    
    public Apostador descobrirApostadorGanhador(int sorteio) throws Exception{
 
        for(AssocRifaApostador apostador : apostadores){

            if(apostador.getApostador().temEsseNumero(sorteio)){

                return apostador.getApostador();

            }

        }
            
        throw new Exception("Ninguem com esse numero");

    }
    
    public void finalizarApostadores() {
       
        for(AssocRifaApostador apostadorTeste : this.apostadores){
        
            apostadorTeste.getApostador().setEstado(EstadoApostador.ComprandoNumeros);
            
        }
        
    }

    public void deletarApostador(Apostador apostador) {
        
        this.apostadores.remove(apostador);
        
    }
    
    public Produto retornarProdutoComMenorPreco(){
    
        Produto produtoMenorPreco = produtos.get(0);
        
        for(int i = 0; i < produtos.size(); i++){
            
            produtoMenorPreco = produtos.get(i);
        
            for(int k = 0; k < produtos.size(); k++){

                if(produtoMenorPreco.getPreco() > produtos.get(k).getPreco()){

                    produtoMenorPreco = produtos.get(k);

                }

            }
        
        }
        
        return produtoMenorPreco;
    
    }
    
    public boolean temApostadorComCpf(int cpf){
    
        for(AssocRifaApostador apostador : apostadores){
        
            if(apostador.getApostador().getCpf() == cpf){
            
                return true;
            
            }
        
        }
        
        return false;
    
    }

    public void desassociarApostador(Apostador apostador) {
        
        AssocRifaApostador apostadorParaRemover = new AssocRifaApostador(this, apostador);
        
        for(AssocRifaApostador apostadorTeste : apostadores){
        
            if(apostadorTeste.getApostador().getCpf() == apostador.getCpf()){
            
                apostadorParaRemover = apostadorTeste;
            
            }
        
        }
        
        apostadores.remove(apostadorParaRemover);
        
    }

    public double calcularLucro() {
       
        return this.custo - (this.numerosParaSortear.size() * precoPorNumero);
        
    }
    
    public String[] getNomeProdutos(){

        String[] nomes = new String[produtos.size()];
        
        int i = 0;
        
        for(Produto produtoTeste : produtos){
        
            nomes[i] = produtoTeste.getNome();
        
            i++;
            
        }

        return nomes;
        
    }
    
    public String[] getNomeApostadores(){
    
        String[] nomes = new String[apostadores.size()];
    
        int i = 0;
        
        for(AssocRifaApostador apostadorTeste : apostadores){
        
            nomes[i] = apostadorTeste.getApostador().getNome();
        
            i++;
            
        }
        
        return nomes;
        
    }

}