/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

/**
 *
 * @author 05380317006
 */
public class Pessoa {
    
    private String nome;
    
    private int cpf;

    public Pessoa(String nome, int cpf){
        
        this.nome = nome;
        
        this.cpf = cpf;
        
    }

    public String getNome() {
        
        return nome;
        
    }

    public int getCpf() {
        
        return cpf;
        
    }

    public void setNome(String nome) {
        
        this.nome = nome;
        
    }

    public void setCpf(int cpf) {
        
        this.cpf = cpf;
        
    }
    
}