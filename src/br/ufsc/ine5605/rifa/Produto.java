/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

/**
 *
 * @author budi
 */
public class Produto {
    
    private String nome;
    
    private int preco;

    public Produto(String nome, int preco) {
        
        this.nome = nome;
        
        this.preco = preco;
        
    }

    public int getPreco() {
        return preco;
    }

    public String getNome() {
        
        return nome;
        
    }

    public void setNome(String nome) {
        
        this.nome = nome;
        
    }

    public void setPreco(int preco) {
        
        this.preco = preco;
        
    }
        
}