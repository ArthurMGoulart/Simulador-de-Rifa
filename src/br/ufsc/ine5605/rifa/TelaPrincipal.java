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
public class TelaPrincipal extends Tela{
    
    private CtrlPrincipal controlador;
    
    public TelaPrincipal(CtrlPrincipal controlador){
    
        super();
        
        this.controlador = controlador;
    
    }

    public void inicia() {
        
        int opcao;
        
         do{
                   
            System.out.println("--------- MENU ---------");

            System.out.println("Opcoes: ");
            
            System.out.println("1 - Criar Rifa");

            System.out.println("2 - Acessar Rifa");
            
            System.out.println("3 - Criar Apostador");
            
            System.out.println("4 - Acessar Apostador");
            
            System.out.println("0 - Encerrar");

            System.out.println("---------********--------");

            System.out.println("opcao: ");

            opcao = entradaDeDados.nextInt();

            entradaDeDados.nextLine();

            switch(opcao){
                
                case 1:
                    criarRifa();
                    break;
                    
                case 2:
                    acessarRifa();
                    break;
                    
                case 3:
                    criarApostador();
                    break;
                    
                case 4:
                    acessarApostador();
                    break;

                }
          
        } while(opcao != 0);
        
    }

    private void criarRifa() {
               
        this.controlador.iniciarCriarRifa();
        
        System.out.println("Rifa Criada Com Sucesso");        
        
    }
    
    private void acessarRifa() {
        
        try{
        
            System.out.println("------ Acessando Rifa ------");

            System.out.println("Insira o Codigo da Rifa: ");

            int codigo = receberValorInteiro();

            this.controlador.acessarRifa(codigo);
        
        }catch(Exception e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }
    }

    private void criarApostador() {
        
        this.controlador.iniciarCriarApostador();
        
        System.out.println("Apostador Criado com Sucesso");
        
    }

    private void acessarApostador() {
        
        try{
        
            System.out.println("------ Acessando Apostador ------");
        
            System.out.println("Insira o Cpf do Apostador: ");
        
            int cpf = receberValorInteiro();
                    
            this.controlador.acessarApostador(cpf);
         
        }catch(Exception e){
                
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
                
        }
          
    }  
        
}