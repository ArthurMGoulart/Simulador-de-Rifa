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
public class TelaApostador extends Tela{
    
    private CtrlApostador controlador;
    
    public TelaApostador(CtrlApostador controlador){
    
        super();
        
        this.controlador = controlador;
    
    
    }
    
    public void inicia(){
        
        int opcao;
        
        if(this.controlador.getApostadorControlado().getEstado() == EstadoApostador.Iniciado){
                  
            do{

                System.out.println("--------- Apostador " + this.controlador.getApostadorControlado().getNome() + " ---------");

                System.out.println("Opcoes: ");

                System.out.println("1 - Associar Rifa");    
                
                System.out.println("2 - Deletar Apostador");
                
                System.out.println("0 - Voltar Ao Menu");

                System.out.println("---------********--------");

                System.out.println("opcao: ");

                opcao = entradaDeDados.nextInt();

                entradaDeDados.nextLine();

                switch(opcao){

                    case 1:
                        associarComRifa();
                        break;

                    case 2:
                        deletarApostador();
                        break;

                    }

            }while(opcao != 0);
            
        }else if(this.controlador.getApostadorControlado().getEstado() == EstadoApostador.ComprandoNumeros){
                               
            do{


                System.out.println("--------- Apostador Comprando Numeros " + this.controlador.getApostadorControlado().getNome() + " ---------");

                System.out.println("Opcoes: ");

                System.out.println("1 - Comprar Numero");
                
                System.out.println("2 - Listar Numeros Disponiveis para Compra");

                System.out.println("3 - Lista Numeros Comprados");  

                System.out.println("0 - Voltar Ao Menu");

                System.out.println("---------********--------");

                System.out.println("opcao: ");

                opcao = entradaDeDados.nextInt();

                entradaDeDados.nextLine();

                switch(opcao){

                    case 1:
                        comprarNumero();
                        break;

                    case 2:
                        listarNumerosDisponiveis();
                        break;

                    case 3:
                        listarNumerosComprados();
                        break;
                        
                    }

            } while(opcao != 0);
            
        }else if(this.controlador.getApostadorControlado().getEstado() == EstadoApostador.Ganhador){
                       
            do{

                System.out.println("--------- Apostador Ganhador " + this.controlador.getApostadorControlado().getNome() + " ---------");

                System.out.println("Opcoes: ");

                System.out.println("1 - Listar Produtos Ganhos");   
                
                System.out.println("2 - Mostrar Lucro");
                
                System.out.println("0 - Voltar Ao Menu");

                System.out.println("---------********--------");

                System.out.println("opcao: ");

                opcao = entradaDeDados.nextInt();

                entradaDeDados.nextLine();

                switch(opcao){

                    case 1:
                        listarProdutosGanhos();
                        break;

                    case 2:
                        mostrarLucro();
                        break;
                }
                
            }while(opcao != 0);
        
        }
        
    }
    
    public void criarApostador() {
        
        try{
        
            System.out.println("--------- Criando Apostador ----------");

            System.out.println("Nome: ");

            String nome = receberTexto();

            System.out.println("Cpf: ");

            int cpf = receberValorInteiro();
            
            this.controlador.criarApostador(nome, cpf);
            
        
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            this.controlador.getControlador().getTela().inicia();
        
        }
        
        System.out.println("Criado Com Sucesso");
        
    }
    
    private void associarComRifa() {
        
        try{
        
            System.out.println("-------- Associando Com Rifa ----------");

            System.out.println("Codigo da Rifa: ");

            int codigo = receberValorInteiro();

            this.controlador.associarComRifa(codigo);

            System.out.println("Associado com Sucesso");

            this.controlador.getControlador().getTela().inicia();
        
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }catch(Exception e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }
        
        System.out.println("Associado com Sucesso");
                
    }
    
    private void deletarApostador() {
        
        try{
        
            System.out.println("------- Deletando Apostador ----------");

            System.out.println("Digite 123 para confirmar");

            int teste = receberValorInteiro();

            if(teste == 123){

                this.controlador.deletarApostador();

                System.out.println("Apostador Deletado com Sucesso");

                this.controlador.getControlador().getTela().inicia();

            }else{

                System.out.println("Nao digitou 123");
                
                mensagemEnter();
                
                inicia();

            }
        
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }    
        
        System.out.println("Deletado Com Sucesso");
                   
    }

    public void comprarNumero() {
        
        System.out.println("------ Comprando Numero ------");
        
        System.out.println("Numero Que Deseja Comprar: ");
        
        int numero = 0;
        
        try{
            
            numero = receberValorInteiro();
            
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }
        try{
            
            this.controlador.comprarNumero(numero);
            
            this.controlador.getApostadorControlado().getRifaAssociada().getRifa().venderNumero(numero);
            
        }catch(IllegalArgumentException e){
            
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }catch(Exception e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }
        
        System.out.println("Numero: " + numero + " Comprado Com Sucesso");
        
    }
    
    private void listarNumerosDisponiveis(){
        
        System.out.println("------ Listando Numeros Disponiveis para Compra ------");
        
        for(Integer numero : this.controlador.getApostadorControlado().getRifaAssociada().getRifa().getNumerosParaVender()){
        
            System.out.println(numero);
        
        }
        
    }

    private void listarNumerosComprados() {
     
        System.out.println("------ Listando Numeros Comprados ------");
        
        int i = 1;
        
        for(Integer numero : this.controlador.getApostadorControlado().getNumerosComprados()){
            
            System.out.println("Numero " + i + ": " + numero);
        
            i++;
            
        }
        
    }   

    private void listarProdutosGanhos() {
        
        System.out.println("------ Listando Produtos Ganhos ------");
        
        int i = 1;
        
        for(Produto produto : this.controlador.getApostadorControlado().getProdutosGanhos()){
            
            System.out.println("Produto " + i + ": " + produto.getNome());
            
            System.out.println("Preco: " + produto.getPreco());
        
            i++;
            
        }
        
    }

    private void mostrarLucro() {
        
        System.out.println("Lucro apostador: " + this.controlador.getApostadorControlado().calcularLucro());
        
    }
    
}